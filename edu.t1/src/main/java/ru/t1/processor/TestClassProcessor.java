package ru.t1.processor;

import java.lang.reflect.Method;
import java.util.*;
import ru.t1.annotation.AfterSuite;
import ru.t1.annotation.AfterTest;
import ru.t1.annotation.BeforeSuite;
import ru.t1.annotation.BeforeTest;
import ru.t1.annotation.CsvSource;
import ru.t1.annotation.Test;
import ru.t1.model.TestMethod;

public class TestClassProcessor {

    public record ProcessedClass(
            Optional<Method> beforeSuite,
            Optional<Method> afterSuite,
            List<Method> beforeTest,
            List<Method> afterTest,
            List<TestMethod> testMethods
    ) {}

    public ProcessedClass processClass(Class<?> clazz) {
        Method bs = null, as = null;
        var bts = new ArrayList<Method>();
        var ats = new ArrayList<Method>();
        var tests = new ArrayList<TestMethod>();

        for (var m : clazz.getDeclaredMethods()) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                ensureStatic(m, "@BeforeSuite");
                if (bs != null) throw new IllegalStateException(
                        "Более одного @BeforeSuite в " + clazz);
                bs = m;
            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                ensureStatic(m, "@AfterSuite");
                if (as != null) throw new IllegalStateException(
                        "Более одного @AfterSuite в " + clazz);
                as = m;
            }
            if (m.isAnnotationPresent(BeforeTest.class)) {
                bts.add(m);
            }
            if (m.isAnnotationPresent(AfterTest.class)) {
                ats.add(m);
            }
            if (m.isAnnotationPresent(Test.class)) {
                var pr = m.getAnnotation(Test.class).priority();
                var csv = Optional.ofNullable(m.getAnnotation(CsvSource.class))
                        .map(CsvSource::value);
                tests.add(new TestMethod(m, pr, csv));
            }
        }

        Collections.sort(tests);

        return new ProcessedClass(
                Optional.ofNullable(bs),
                Optional.ofNullable(as),
                bts, ats, tests
        );
    }

    private void ensureStatic(Method m, String ann) {
        if ((m.getModifiers() & java.lang.reflect.Modifier.STATIC) == 0) {
            throw new IllegalStateException(ann + " должен быть static: " + m);
        }
    }
}
