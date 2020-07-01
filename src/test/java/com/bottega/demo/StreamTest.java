package com.bottega.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class StreamTest {

    @Test
    public void example_1() throws Exception {
        Optional<Integer> optionalInteger = Optional.ofNullable(42);

        Stream<Integer> integerStream = Stream.of(1, 2, 3);
        Stream<Integer> arrayStream = Arrays.stream(new Integer[]{1, 2, 3});

        Stream<Integer> collectionStream = Arrays.asList(1, 2, 3).stream();
    }

    @Test(expected = IllegalStateException.class)
    public void example_2() throws Exception {
        Stream<Integer> integerStream = Stream.of(1, 2, 3);

        integerStream.forEach(System.out::println);
        integerStream.forEach(System.out::println);
    }

    @Test
    public void example_3() throws Exception {
        Stream<Integer> integerStream = Stream.of(1, 2, 3);

        // nie wypisuje nic
        integerStream.map(i -> {
            System.out.println(i);
            return i;
        });

        // wypisuje wszystkie elementy, bo forEach jest metodą terminalną
        integerStream.forEach(System.out::println);
    }

    @Test
    public void example_4() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3);

//        Stream<Integer> stream = integers.stream();
//        Stream<Integer> integerStream = stream.map(i -> 2 * i);
//        integerStream.forEach(System.out::println);

        integers.stream()
          .map(i -> 2 * i)
          .forEach(System.out::println);
    }

    @Test
    public void example_5() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        integers.stream()
          .filter(i -> i > 1)            // 1
          .map(i -> i * 2)               // 2
          .map(i -> i + 1)               // 3
          .forEach(System.out::println); // 4
    }

    @Test
    public void example_6() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        List<Integer> list = integers.stream()
          .filter(i -> i > 1)
          .map(i -> i * 2)
          .map(i -> i + 1)
          .collect(toList());

        Set<Integer> set = integers.stream()
          .filter(i -> i > 1)
          .map(i -> i * 2)
          .map(i -> i + 1)
          .collect(toSet());

        LinkedList<Integer> linkedList = integers.stream()
          .filter(i -> i > 1)
          .map(i -> i * 2)
          .map(i -> i + 1)
          .collect(toCollection(LinkedList::new));

        Map<Integer, String> collect = Stream.of("a", "bb", "ccc", "dd")
          .map(String::toUpperCase)
          .collect(toMap(String::length, string -> string,
            (s1, s2) -> s2));

        System.out.println(collect);
    }

    @Test
    public void example_7() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        Optional<Integer> any = integers.stream()
          .skip(1)
          .limit(10)
          .filter(i -> i > 1)
          .map(i -> i * 2)
          .map(i -> i + 1)
          .findAny();
        //.findFirst()
    }

    @Test
    public void example_8() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        Optional<Integer> reduce = integers.stream()
          .reduce((i1, i2) -> i1 + i2);

        Integer reduce2 = integers.stream()
          .reduce(0, (i1, i2) -> i1 + i2);

        // i1:0, i2: 1
        // i1:1, i2: 2
        // i1:2, i3: 3
    }

    @Test
    public void example_9() throws Exception {

        List<List<Integer>> lists = Arrays.asList(Arrays.asList(0), Arrays.asList(1, 2));

        System.out.println(lists);

        lists.stream()
          .flatMap(Collection::stream)
          .forEach(System.out::println);
    }

    @Test
    public void example_10() throws Exception {

        List<String> strings = Arrays.asList("House", "Dog");

        strings.stream()
          .flatMap(str -> Stream.of(str.toLowerCase(), str.toUpperCase()))
          .forEach(System.out::println);
    }

    @Test
    public void example_11() throws Exception {
        List<String> strings = Arrays.asList("House", "Dog");

        boolean result = strings.stream()
          .allMatch(str -> str.length() > 20);

        boolean result2 = strings.stream()
          .noneMatch(str -> str.length() > 20);

        System.out.println(result2);
    }
}
