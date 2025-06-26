package ru.t1;

import ru.t1.annotation.AfterSuite;
import ru.t1.annotation.AfterTest;
import ru.t1.annotation.BeforeSuite;
import ru.t1.annotation.BeforeTest;
import ru.t1.annotation.CsvSource;
import ru.t1.annotation.Test;

public class MyTests {

    @BeforeSuite
    public static void initAll() {
        System.out.println(">>> BeforeSuite");
    }

    @AfterSuite
    public static void cleanupAll() {
        System.out.println(">>> AfterSuite");
    }

    @BeforeTest
    public void beforeEach() {
        System.out.println("[BeforeTest]");
    }

    @AfterTest
    public void afterEach() {
        System.out.println("[AfterTest]");
    }

    @Test(priority = 9)
    @CsvSource("42, Hello, 3, false")
    public void testHigh(int a, String s, int b, boolean flag) {
        System.out.printf("testHigh: %d, %s, %d, %b%n", a, s, b, flag);
    }

    @Test
    public void defaultPriority() {
        System.out.println("defaultPriority");
    }

    @Test(priority = 1)
    public void lowPriority() {
        System.out.println("lowPriority");
    }
}