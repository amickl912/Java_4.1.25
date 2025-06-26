package ru.t1.model;

import java.lang.reflect.Method;
import java.util.Optional;

public record TestMethod(
        Method method,
        int priority,
        Optional<String> csvSource
) implements Comparable<TestMethod> {

    @Override
    public int compareTo(TestMethod other) {
        return Integer.compare(other.priority, this.priority);
    }
}

