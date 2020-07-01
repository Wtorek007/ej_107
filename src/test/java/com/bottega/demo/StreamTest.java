package com.bottega.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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
}
