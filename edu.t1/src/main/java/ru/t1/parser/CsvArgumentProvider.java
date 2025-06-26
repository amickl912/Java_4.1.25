package ru.t1.parser;

import java.lang.reflect.Method;
import java.util.Arrays;
import ru.t1.annotation.CsvSource;

public class CsvArgumentProvider implements ArgumentProvider {

    @Override
    public Object[] getArguments(Method method) {
        var ann = method.getAnnotation(CsvSource.class);
        if (ann == null) return new Object[0];

        String[] parts = Arrays.stream(ann.value().split(","))
                .map(String::trim)
                .toArray(String[]::new);
        var params = method.getParameterTypes();
        if (parts.length != params.length) {
            throw new IllegalArgumentException(
                    "Арность CsvSource <> параметрам метода: " +
                            method
            );
        }

        Object[] args = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            args[i] = convert(parts[i], params[i]);
        }
        return args;
    }

    private Object convert(String s, Class<?> type) {
        return switch (type.getName()) {
            case "int", "java.lang.Integer"   -> Integer.parseInt(s);
            case "long", "java.lang.Long"     -> Long.parseLong(s);
            case "double", "java.lang.Double" -> Double.parseDouble(s);
            case "boolean", "java.lang.Boolean"-> Boolean.parseBoolean(s);
            case "char", "java.lang.Character"-> {
                if (s.length() != 1) throw new IllegalArgumentException(s);
                yield s.charAt(0);
            }
            case "java.lang.String"           -> s;
            default -> throw new UnsupportedOperationException(
                    "Неизвестный тип: " + type
            );
        };
    }
}
