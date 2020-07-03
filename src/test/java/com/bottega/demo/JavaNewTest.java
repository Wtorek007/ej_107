package com.bottega.demo;

import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.teeing;
import static java.util.stream.Collectors.toList;

public class JavaNewTest {

    // https://github.com/AdoptOpenJDK/jdk-api-diff

    @Test
    public void example_optional() throws Exception {
        String
        Optional<Integer> optional = Optional.empty();
        Optional<Integer> or = optional.or(() -> Optional.of(42));

        Optional<Integer> o2 = Optional.ofNullable(2);
        Integer integer = o2.orElseThrow();
    }

    @Test
    public void example_stream() throws Exception {
        Stream<Object> objectStream = Stream.ofNullable(null);
        objectStream.forEach(System.out::println);

        Stream.iterate(0, i -> i + 1)
          .takeWhile(i -> i < 20)
          .forEach(System.out::println);
    }

    @Test
    public void example_teeing() throws Exception {

        Long collect = Stream.of(1, 2, 3)
          .collect(teeing(counting(), summingInt(i -> i), (count, sum) -> sum / count));

        System.out.println(collect);
    }

    static Function<List<Optional<Integer>>, List<Integer>> L12_filterPresentJDK14() {
        return list -> list.stream()
          .flatMap(Optional::stream)
          .collect(toList());
    }
}
