package ru.t1;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

import java.util.*;
public class TaskUtilsDataProvider {

    public static Stream<Arguments> thirdMaxData() {
        return Stream.of(
                Arguments.of(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13), 10),
                Arguments.of(List.of(3, 3, 2, 2, 1), 2),
                Arguments.of(List.of(10, 9, 8), 8)
        );
    }

    public static Stream<Arguments> thirdUniqueMaxData() {
        return Stream.of(
                Arguments.of(List.of(5, 2, 10, 9, 4, 3, 10, 1, 13), 9),
                Arguments.of(List.of(3, 3, 2, 2, 1), 1),
                Arguments.of(List.of(10, 9, 8), 8)
        );
    }

    public static Stream<Arguments> longestWordData() {
        return Stream.of(
                Arguments.of(List.of("cat", "elephant", "dog"), "elephant"),
                Arguments.of(List.of("ant", "bear", "zebra"), "zebra")
        );
    }

    public static Stream<Arguments> wordFrequencyData() {
        return Stream.of(
                Arguments.of("java java kotlin", Map.of("java", 2L, "kotlin", 1L)),
                Arguments.of("a b a b c", Map.of("a", 2L, "b", 2L, "c", 1L))
        );
    }

    public static Stream<Arguments> sortWordsData() {
        return Stream.of(
                Arguments.of(List.of("java", "go", "c", "scala", "js"),
                        List.of("c", "go", "js", "java", "scala")),
                Arguments.of(List.of("abc", "a", "ab", "b", "aa"),
                        List.of("a", "b", "aa", "ab", "abc"))
        );
    }

    public static Stream<Arguments> longestWordInLineArrayData() {
        return Stream.of(
                Arguments.of(new String[]{
                        "java kotlin scala go rust",
                        "csharp python javascript sql dart"
                }, Set.of("javascript", "csharp")),

                Arguments.of(new String[]{
                        "one two three",
                        "four five sixseven"
                }, Set.of("sixseven"))
        );
    }
}
