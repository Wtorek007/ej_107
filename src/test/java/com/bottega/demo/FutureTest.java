package com.bottega.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class FutureTest {

    private static final Logger log = LoggerFactory.getLogger(FutureTest.class);

    @Test
    public void example_1() throws Exception {
        // public interface Future<V> {}

        Future<Integer> result = Executors.newFixedThreadPool(1)
          .submit(() -> {
              Thread.sleep(2000);
              return 42;
          });

        System.out.println(result.get());
    }

    @Test
    public void example_2() throws Exception {
        ExecutorService e = Executors.newFixedThreadPool(1);

        CompletableFuture<Integer> result1 = calculateWithDelay(42, e);

        result1
          .thenApply(i -> i + 1)
          .thenApply(i -> i * 2)
          .thenApply(i -> "Result: " + i)
          .thenAccept(System.out::println)
          .join();
    }

    @Test
    public void example_3() throws Exception {
        ExecutorService e = Executors.newFixedThreadPool(2);

        CompletableFuture<Integer> result1 = calculateWithDelay(1, e);
        CompletableFuture<Integer> result2 = calculateWithDelay(2, e);

        result1
          .acceptEither(result2, System.out::println)
          .join();
    }

    @Test
    public void example_4() throws Exception {
        ExecutorService e = Executors.newFixedThreadPool(2);

        CompletableFuture<Integer> result1 = calculateWithDelay(1, e);
        CompletableFuture<Integer> result2 = calculateWithDelay(2, e);

        result1
          .thenCombine(result2, Integer::sum)
          .thenAccept(System.out::println)
          .join();
    }

    @Test
    public void example_5() throws Exception {
        ExecutorService e = Executors.newFixedThreadPool(2);

        calculateWithDelay(1, e)
          .thenCompose(r -> calculateWithDelay(r, e))
          .thenAccept(System.out::println)
          .join();
    }

    @Test
    public void example_6() {
        ExecutorService e = Executors.newFixedThreadPool(2);

        CompletableFuture<Void> r1 = calculateWithDelay(1, e)
          .thenAcceptAsync(System.out::println);

        CompletableFuture<Void> r2 = calculateWithDelay(2, e)
          .thenAccept(System.out::println);

        CompletableFuture.allOf(r1, r2)
          .join();
    }

    @Test
    public void example_7() throws Exception {
        ExecutorService e = Executors.newFixedThreadPool(1, r -> new Thread(r, "nasz-watek"));

        CompletableFuture<Integer> r = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            return 42;
        }, e);

        System.out.println(r.join());
    }

    @Test
    public void example_8() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.completedFuture(42);
        CompletableFuture<String> f2 = CompletableFuture.completedFuture("42");
        CompletableFuture<Thread> f3 = CompletableFuture.completedFuture(new Thread());

        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(f1, f2, f3);
    }

    @Test
    public void example_9() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.completedFuture(42);
        CompletableFuture<String> f2 = CompletableFuture.completedFuture("42");
        CompletableFuture<Thread> f3 = CompletableFuture.completedFuture(new Thread());

        CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2, f3);
    }

    private CompletableFuture<Integer> calculateWithDelay(int i, ExecutorService e) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                log.info("Processing {}", i);
                Thread.sleep(100 + ThreadLocalRandom.current().nextInt(5000));
            } catch (InterruptedException interruptedException) {
                //
            }
            return i;
        }, e);
    }
}
