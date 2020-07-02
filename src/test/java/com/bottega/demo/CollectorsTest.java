package com.bottega.demo;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

public class CollectorsTest {

    @Test
    public void example_1() throws Exception {
        Map<Integer, List<String>> result = Stream.of("aa", "bb", "ccc")
          .collect(groupingBy(String::length));

        System.out.println(result);
    }

    @Test
    public void example_2() throws Exception {
        Map<Integer, List<String>> result = Stream.of("aa", "bb", "ccc")
          .collect(
            groupingBy(String::length,
            mapping(String::toUpperCase,
              toList())));

        System.out.println(result);
    }

    @Test
    public void example_3() throws Exception {
        List<String> collect = Stream.of("aa", "bb", "ccc")
          .collect(toList());

        collect.add("foo");

        System.out.println(collect);

        //

        List<String> andThen = Stream.of("aa", "bb", "ccc")
          .collect(collectingAndThen(toList(), list -> {
              list.add("foo");
              return list;
          }));

        System.out.println(andThen);
    }

    @Test
    public void example_4() throws Exception {
        String collect = Stream.of("aa", "bb", "ccc")
          .collect(joining(":", "PREFIX: ", " :SUFFIX"));

        System.out.println(collect);
    }
}
