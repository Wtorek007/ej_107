package com.bottega.main;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class FunctionalInterfaces {
    public static void main(String[] args) {

        Function<Integer, Integer> function1 = i -> i + 1;
        BiFunction<Integer, Integer, Integer> bifunction1 = (i1, i2) -> i1 + i2;

        Supplier<Integer> supplier = () -> 42; // Function<Void, Integer>
        Consumer<Integer> consumer = i -> { System.out.println(i);}; // Function<Integer, Void>

        Runnable runnable = () -> {System.out.println("Hello");}; // Function<Void, Void>

        Predicate<Integer> predicate = i -> true; // Function<Integer, Boolean>

        UnaryOperator<Integer> unaryOperator = i -> i + 1; // Function<Integer, Integer>
        BinaryOperator<Integer> binaryOperator = (Integer i1, Integer i2) -> i1 + i2; // Function<Integer, Integer>

        UserIdProvider provider = () -> 42;
    }
}
