package com.for_comprehension.function.E07;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;

class ParallelStream {
    private static final Logger log = LoggerFactory.getLogger(ParallelStream.class);

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(10);
        List<Integer> input = Arrays.asList(1, 2, 3, 4);

        CompletableFuture<List<Integer>> result = parallelAsync(input, e -> {
            log.info("Processing {}", e);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException interruptedException) {
                //
            }
            return e;
        }, ex);

        System.out.println(result.join());

        ex.shutdown();
    }

    static <T, R> List<R> parallelSync(Collection<T> input, Function<T, R> task, ExecutorService executor) {
        return parallelAsync(input, task, executor).join();

//        return input.stream()
//          .map(e -> CompletableFuture.supplyAsync(() -> task.apply(e), executor))
//          .collect(Collectors.collectingAndThen(Collectors.toList(),
//            list -> list.stream()
//              .map(CompletableFuture::join)
//              .collect(Collectors.toList())));
    }

    static <T, R> CompletableFuture<List<R>> parallelAsync(Collection<T> input, Function<T, R> task, ExecutorService executor) {
        return input.stream()
          .map(e -> CompletableFuture.supplyAsync(() -> task.apply(e), executor))
          .collect(toFuture());
    }

    private static <R> Collector<CompletableFuture<R>, Object, CompletableFuture<List<R>>> toFuture() {
        return collectingAndThen(Collectors.toList(),
          list -> CompletableFuture.allOf(list.toArray(new CompletableFuture[0]))
            .thenApply(__ -> list.stream()
              .map(CompletableFuture::join)
              .collect(Collectors.toList())));
    }
}
