package com.for_comprehension.function.E06;

import com.for_comprehension.function.misc.User;
import com.for_comprehension.function.misc.UsersClient;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

class CompletableFutures {

    private static final ExecutorService executor = Executors.newFixedThreadPool(20);

    private static final UsersClient usersClient = new UsersClient();

    /**
     * Complete incoming {@link CompletableFuture} manually with value 42
     */
    static Consumer<CompletableFuture<Integer>> L1_manualCompletion() {
        return f -> {
            // TODO
        };
    }

    /**
     * Complete incoming {@link CompletableFuture} exceptionally with a {@link NullPointerException}
     */
    static Consumer<CompletableFuture<Integer>> L2_manualExceptionCompletion() {
        return f -> {
            // TODO
        };
    }

    /**
     * Run {@link UsersClient#getUserById(Integer)} asynchronously
     * Use the provided id to look up the user
     *
     *
     */
    static Function<Integer, CompletableFuture<User>> L3_runAsync() {
        return id -> {
            return null;
        };
    }

    /**
     * Run {@link UsersClient#getUserById(Integer)} asynchronously on a given thread pool
     * Use the provided id to look up the user
     *
     * Essentially, the same as above + execution on a provided thread pool
     */
    static BiFunction<Integer, ExecutorService, CompletableFuture<User>> L4_runAsyncOnACustomPool() {
        return (id, executor) -> {
            return null;
        };
    }

    /**
     * Run {@link UsersClient#getUserById(Integer)}
     * on two different ids and return both users in a List
     *
     * {@link CompletableFuture#thenCombine(CompletionStage, BiFunction)}
     *
     */
    static BiFunction<Integer, Integer, CompletableFuture<List<User>>> L5_runAsyncAndCombine() {
        return (id, id2) -> {
            return null;
        };
    }

    /**
     * Return a combined future which completes with a value of the first completed future
     * <p>
     * {@link CompletableFuture#thenCombine(CompletionStage, BiFunction)}
     */
    static BiFunction<CompletableFuture<Integer>, CompletableFuture<Integer>, CompletableFuture<Integer>> L6_composeFutures() {
        return (f1, f2) -> {
            return null;
        };
    }

    /**
     * Given two futures, return the result of whichever completes first
     *
     * {@link CompletableFuture#anyOf(CompletableFuture[])}
     *
     */
    static <T> BiFunction<CompletableFuture<T>, CompletableFuture<T>, T> L7_returnValueOfTheFirstCompleted() {
        return (f1, f2) -> {
            return null;
        };
    }

    /**
     * Given a list of futures, convert it to a future containing a list of all results
     *
     * {@link CompletableFuture#allOf(CompletableFuture[])}
     */
    static <T> Function<List<CompletableFuture<T>>, CompletableFuture<List<T>>> L8_returnResultsAsList() {
        return futures -> {
            return null;
        };
    }

}
