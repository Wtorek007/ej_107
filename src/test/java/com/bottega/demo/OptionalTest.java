package com.bottega.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class OptionalTest {

    @Test
    public void declarative_imperative_1() throws Exception {
        List<Integer> integers = Arrays.asList(1, 2, 3);

        // imperative
        for (int i = 0; i < integers.size(); i++) {
            System.out.println(integers.get(i));
        }

        // declarative
        integers.forEach(System.out::println);
    }

    // imperative Optional :) unikać
    @Test
    public void optional_faulty() throws Exception {
        Optional<Integer> maybeInteger = Optional.of(42);

        if (maybeInteger.isPresent()) {
            Integer integer = maybeInteger.get();
        } else {
            throw new IllegalStateException();
        }
    }

    @Test
    public void optional_2() throws Exception {
        Optional<Integer> maybeInteger = Optional.of(1);

        Optional<Integer> maybeDoubleInteger = maybeInteger
          .filter(value -> value > 123)
          .map(value -> 2 * value)
          .map(value -> value - 1)
          .map(value -> 3 * value);

        Integer result = maybeInteger.orElse(42);
        Integer result2 = maybeInteger.orElseGet(() -> 42);
        Integer result3 = maybeInteger.orElseThrow(IllegalStateException::new);
    }

    @Test
    public void optional_3() throws Exception {
        Optional<Integer> maybeInteger = Optional.of(1);

        Optional<String> s = maybeInteger
          .flatMap(i -> findUser(i));

        Optional<Integer> s2 = maybeInteger
          .map(i -> 42)
          .flatMap(i -> Optional.of(42));
    }

    Optional<String> findUser(int id) {
        return Optional.of("");
    }

    @Test
    public void example_4() throws Exception {
        Optional<Integer> maybeInteger = Optional.of(1);

        Integer result = maybeInteger.orElseGet(() -> slowFindUser(1231));

        System.out.println(result);
    }

    Integer slowFindUser(int id) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            //
        }
        return 429198371;
    }
}
