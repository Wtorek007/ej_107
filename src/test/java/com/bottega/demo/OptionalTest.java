package com.bottega.demo;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

        Integer result = maybeInteger.orElse(42);
        Integer result2 = maybeInteger.orElseGet(() -> 42);
        Integer result3 = maybeInteger.orElseThrow(IllegalStateException::new);
    }

    Optional<String> findUser(int id) {
        return Optional.of("");
    }
}
