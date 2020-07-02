package com.bottega.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelStreamTest {

    private static final Logger log = LoggerFactory.getLogger(ParallelStreamTest.class);

    @Test
    public void example_1() throws Exception {
        List<Integer> list = Stream.iterate(0, i -> i + 1)
          .limit(40).collect(Collectors.toList());

        List<Integer> collect = list.parallelStream()
          .map(i -> process(i))
          .collect(Collectors.toList());
    }

    public static int process(int input) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO
        }

        log.info("Processing {}", input);

        return input;
    }

    @Test
    public void example_2() throws Exception {

        List<Integer> list = Stream.iterate(0, i -> i + 1)
          .limit(40).collect(Collectors.toList());

        ExecutorService executor = Executors.newFixedThreadPool(40);

        ArrayList<Future<Integer>> results = new ArrayList<>();
        for (Integer integer : list) {
            Future<Integer> submit = executor.submit(() -> process(integer));
            results.add(submit);
        }

    }

}
