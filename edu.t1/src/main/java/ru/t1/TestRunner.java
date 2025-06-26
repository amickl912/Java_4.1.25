package ru.t1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import ru.t1.annotation.AfterSuite;
import ru.t1.annotation.AfterTest;
import ru.t1.annotation.BeforeSuite;
import ru.t1.annotation.BeforeTest;
import ru.t1.annotation.CsvSource;
import ru.t1.annotation.Test;
import ru.t1.executor.TestExecutor;
import ru.t1.parser.ArgumentProvider;

public class TestRunner {
    public static void runTests(Class<?> testClass) {
        var executor = new TestExecutor(ArgumentProvider.csv());
        executor.execute(testClass);
    }
}