package ru.t1.parser;


import java.lang.reflect.Method;

public interface ArgumentProvider {
    Object[] getArguments(Method method);

    static ArgumentProvider csv() {
        return new CsvArgumentProvider();
    }
}