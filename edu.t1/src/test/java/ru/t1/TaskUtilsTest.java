package ru.t1;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskUtilsTest {

    @ParameterizedTest
    @MethodSource("ru.t1.TaskUtilsDataProvider#thirdMaxData")
    void testThirdMax(List<Integer> input, int expected) {
        assertEquals(expected, TaskUtils.thirdMax(input));
    }

    @ParameterizedTest
    @MethodSource("ru.t1.TaskUtilsDataProvider#thirdUniqueMaxData")
    void testThirdMaxUnique(List<Integer> input, int expected) {
        assertEquals(expected, TaskUtils.thirdMaxUnique(input));
    }

    @Test
    void testTop3OldestEngineers() {
        List<Employee> employees = List.of(
                new Employee("Анна", 30, "Инженер"),
                new Employee("Борис", 42, "Инженер"),
                new Employee("Виктор", 28, "Аналитик"),
                new Employee("Григорий", 45, "Инженер"),
                new Employee("Денис", 40, "Инженер")
        );
        List<String> result = TaskUtils.top3OldestEngineers(employees);
        assertEquals(List.of("Григорий", "Борис", "Денис"), result);
    }

    @Test
    void testAverageEngineerAge() {
        List<Employee> employees = List.of(
                new Employee("Анна", 30, "Инженер"),
                new Employee("Борис", 42, "Инженер"),
                new Employee("Григорий", 45, "Инженер"),
                new Employee("Виктор", 28, "Аналитик")
        );
        assertEquals((30 + 42 + 45) / 3.0, TaskUtils.averageEngineerAge(employees));
    }

    @ParameterizedTest
    @MethodSource("ru.t1.TaskUtilsDataProvider#longestWordData")
    void testLongestWord(List<String> input, String expected) {
        assertEquals(expected, TaskUtils.longestWord(input));
    }

    @ParameterizedTest
    @MethodSource("ru.t1.TaskUtilsDataProvider#wordFrequencyData")
    void testWordFrequency(String input, Map<String, Long> expected) {
        assertEquals(expected, TaskUtils.wordFrequency(input));
    }

    @ParameterizedTest
    @MethodSource("ru.t1.TaskUtilsDataProvider#sortWordsData")
    void testSortByLengthAndAlphabet(List<String> input, List<String> expected) {
        assertEquals(expected, TaskUtils.sortByLengthAndAlphabet(input));
    }

    @ParameterizedTest
    @MethodSource("ru.t1.TaskUtilsDataProvider#longestWordInLineArrayData")
    void testLongestWordInLineArray(String[] lines, Set<String> expectedSet) {
        String result = TaskUtils.longestWordInLineArray(lines);
        assertTrue(expectedSet.contains(result));
    }
}
