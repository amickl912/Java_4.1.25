package ru.t1.executor;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import ru.t1.model.TestMethod;
import ru.t1.parser.ArgumentProvider;
import ru.t1.processor.TestClassProcessor;

public class TestExecutor {

    private final ArgumentProvider argProvider;

    public TestExecutor(ArgumentProvider argProvider) {
        this.argProvider = argProvider;
    }

    public void execute(Class<?> clazz) {
        var processor = new TestClassProcessor();
        TestClassProcessor.ProcessedClass pc = processor.processClass(clazz);

        // 1) BeforeSuite (static)
        pc.beforeSuite().ifPresent(method -> invoke(method, null, new Object[0]));

        // 2) Для каждого @Test
        for (TestMethod tm : pc.testMethods()) {
            Method testMethod = tm.method();

            // — создаём экземпляр, если метод не static
            Object instance = Modifier.isStatic(testMethod.getModifiers())
                    ? null
                    : createInstance(clazz);

            // — вызываем все @BeforeTest на этом же экземпляре
            for (Method before : pc.beforeTest()) {
                invoke(before, instance, new Object[0]);
            }

            // — резолвим аргументы (CsvSource или пустой массив)
            Object[] args = tm.csvSource()
                    .map(src -> argProvider.getArguments(testMethod))
                    .orElse(new Object[0]);

            // — сам @Test
            invoke(testMethod, instance, args);

            // — все @AfterTest
            for (Method after : pc.afterTest()) {
                invoke(after, instance, new Object[0]);
            }
        }

        // 3) AfterSuite (static)
        pc.afterSuite().ifPresent(method -> invoke(method, null, new Object[0]));
    }

    private Object createInstance(Class<?> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать экземпляр " + clazz, e);
        }
    }

    private void invoke(Method method, Object target, Object[] args) {
        try {
            method.setAccessible(true);
            method.invoke(target, args);
        } catch (Exception e) {
            // Если из invoke пришло InvocationTargetException, то e.getCause() — реальная ошибка из теста
            Throwable root = e.getCause() != null ? e.getCause() : e;
            throw new RuntimeException("Ошибка при выполнении " + method, root);
        }
    }
}
