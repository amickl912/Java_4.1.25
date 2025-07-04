package ru.t1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaskUtils {

    public static int thirdMax(List<Integer> list) {
        return list.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow();
    }

    public static int thirdMaxUnique(List<Integer> list) {
        return list.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow();
    }

    public static List<String> top3OldestEngineers(List<Employee> employees) {
        return employees.stream()
                .filter(e -> "Инженер".equals(e.position()))
                .sorted(Comparator.comparingInt(Employee::age).reversed())
                .limit(3)
                .map(Employee::name)
                .toList();
    }

    public static double averageEngineerAge(List<Employee> employees) {
        return employees.stream()
                .filter(e -> "Инженер".equals(e.position()))
                .mapToInt(Employee::age)
                .average()
                .orElse(0);
    }

    public static String longestWord(List<String> words) {
        return words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    public static Map<String, Long> wordFrequency(String input) {
        return Arrays.stream(input.split(" "))
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
    }

    public static List<String> sortByLengthAndAlphabet(List<String> words) {
        return words.stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder()))
                .toList();
    }

    public static String longestWordInLineArray(String[] lines) {
        return Arrays.stream(lines)
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
}
