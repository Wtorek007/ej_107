package com.for_comprehension.function.E03;

import java.time.LocalDate;
import java.time.Year;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

class Streams {

    private static void L0_cubeComposer() {
        // https://david-peter.de/cube-composer/
    }

    /**
     * Uppercase all strings in a list
     * {@link Stream#map(Function)}
     */
    static Function<List<String>, List<String>> L1_upperCaseAll() {
        return input ->
          input.stream()
          .map(String::toUpperCase)
          .collect(toList());
    }

    /**
     * Uppercase all list elements and discard names containing less than 6 characters
     * {@link Stream#filter(Predicate)}
     */
    static Function<List<String>, List<String>> L2_upperCaseAllAndFilter() {
        return input -> input
          .stream()
          .filter(str -> str.length() > 6)
          .map(str -> str.toUpperCase())
          .collect(toList());
    }

    /**
     * Find the longest name
     * {@link Stream#max(Comparator)}
     * {@link Stream#sorted()} {@link Stream#findFirst()}
     */
    static Function<List<String>, String> L3_findTheLongestName() {
        return input -> {
            return input.stream()
              .max(Comparator.comparingInt(String::length))
              .orElseThrow(IllegalStateException::new);
        };
    }

    /**
     * Find the longest name
     * {@link Stream#max(Comparator)}
     * {@link Stream#sorted()} {@link Stream#findFirst()}
     */
    static Function<List<String>, String> L3_findTheLongestName2() {
        return input -> {
            return input.stream()
              .sorted(Comparator.comparingInt(String::length).reversed())
              .findFirst()
              .orElseThrow(IllegalStateException::new);
        };
    }



    /**
     * Flatten a nested list structure
     * {@link Stream#flatMap(Function)}
     */
    static Function<List<List<Integer>>, List<Integer>> L4_flatten() {
        return input -> {
            return input.stream()
              .flatMap(integers -> integers.stream())
              .collect(toList());
        };
    }

    /**
     * Eliminate duplicates
     * {@link Stream#distinct()}
     */
    static Function<List<Integer>, List<Integer>> L5_distinctElements() {
        return input -> {
            return input.stream()
              .distinct()
              .collect(toList());
        };
    }

    /**
     * Duplicate the elements of a list
     */

    // 1,2,3 -> 1,1,2,2,3,3
    static Function<List<Integer>, List<Integer>> L6_duplicateElements() {
        return input -> input.stream()
          .flatMap(e -> Stream.of(e, e))
          .collect(toList());
    }

    /**
     * Duplicate the elements of a list a given number of times
     * {@link Stream#generate(Supplier)}
     */
    static Function<List<Integer>, List<Integer>> L7_duplicateElementsNTimes(int givenNumberOfTimes) {
        return input ->
          input.stream()
            .flatMap(i -> Stream.generate(() -> i).limit(givenNumberOfTimes))
            .collect(toList());
    }

    ;

    /**
     * Create a stream only with multiples of 3, starting from 0, size of 10
     * {@link Stream#iterate}
     */
    static Supplier<List<Integer>> L8_generate3s() {
        return () -> Stream.iterate(0, i -> i + 3)
          .limit(10)
          .collect(toList());
    }

    /**
     * Find five consecutive leap years since 2000
     * {@link Stream#iterate(Object, UnaryOperator)}
     * {@link LocalDate#isLeapYear()}
     */
    static Supplier<List<Integer>> L9_leapYears() {
        return () ->
          Stream.iterate(Year.of(2000), year -> year.plusYears(1))
          .filter(Year::isLeap)
          .limit(5)
          .map(Year::getValue)
          .collect(toList());
    }

    /**
     * Rotate a list N places to the left
     * {@link Stream#concat(Stream, Stream)}
     * {@link Stream#skip(long)}
     * {@link Stream#limit(long)}
     */

    // [1, 2, 3]
    // [2, 3, 1]
    // [3, 1, 2]
    // [1, 2, 3]
    static UnaryOperator<List<Integer>> L10_rotate(int n) {
        return input ->
          Stream.concat(input.stream(), input.stream()) // [1, 2, 3] [1, 2, 3]
          .skip(n % input.size())
          .limit(input.size())
          .collect(toList());
    }

    /**
     * Check if all elements sum up to 100, if no throw an exception
     */
    static Predicate<List<Double>> L11_sum() throws IllegalStateException {
        return input -> {
            return input.stream()
              .reduce((d1, d2) -> d1 + d2)
              .filter(sum -> sum == 100)
              .map(i -> true)
              .orElseThrow(IllegalStateException::new);
        };
    }

    /**
     * Convert a {@link List} of {@link Optional} to a {@link List} of only not-empty values
     * <p>
     * Advanced challenge: use {@link Stream#flatMap(Function)}
     */
    static Function<List<Optional<Integer>>, List<Integer>> L12_filterPresent() {
        return list -> list.stream()
          .filter(item -> item.isPresent())
          .map(item -> item.get())
          .collect(Collectors.toList());
    }

    /**
     * Convert a {@link List} of {@link Optional} to a {@link List} of only not-empty values
     * <p>
     * Advanced challenge: use {@link Stream#flatMap(Function)}
     */
    static Function<List<Optional<Integer>>, List<Integer>> L12_filterPresent2() {
        return list -> list.stream()
          .flatMap(optional -> optional.map(Stream::of).orElseGet(Stream::empty))
          .collect(toList());
    }

    /* Od JDK 9
    static Function<List<Optional<Integer>>, List<Integer>> L12_filterPresentJDK14() {
        return list -> list.stream()
          .flatMap(optional -> optional.stream())
          .collect(toList());
    }*/
}
