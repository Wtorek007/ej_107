package com.for_comprehension.function.E07;

import com.pivovarit.collectors.ParallelCollectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static com.pivovarit.collectors.ParallelCollectors.parallel;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

class ParallelCollectorStream {
    private static final Logger log = LoggerFactory.getLogger(ParallelCollectorStream.class);

    public static void main(String[] args) {

        ExecutorService ex = Executors.newFixedThreadPool(100);

        IntStream.range(0, 100).boxed()
          .collect(ParallelCollectors.parallelToOrderedStream(i -> withDelay(i), ex, 10))
          .forEach(System.out::println);

        ex.shutdown();
    }

    private static <T> T withDelay(T value) {
        try {
            log.info("Processing {}", value);
            Thread.sleep(400 + ThreadLocalRandom.current().nextInt(5000));
        } catch (InterruptedException e) {
            // ignore shamefully
        }

        return value;
    }
}
