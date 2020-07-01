package com.bottega.main;

import java.util.function.BiFunction;
import java.util.function.Function;

class Lambdas {

    public static void main(String[] args) {
        int a = 42;
        BiFunction<Integer, Integer, Integer> add_anonymous = new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };

        BiFunction<Integer, Integer, Integer> add_lambda1 = (a1, b) -> a1 + b;
        BiFunction<Integer, Integer, Integer> add_lambda2 = (a1, b) -> {
            System.out.println("Hello World!");
            return a1 + b;
        };
        BiFunction<Integer, Integer, Integer> add_method_ref = Integer::sum;

        Function<Integer, Integer> lambda1 = i -> i;
        Function<Integer, Integer> lambda2 = (i) -> i;
        Function<Integer, Integer> lambda3 = (Integer i) -> i;
    }

    public Integer add(int a, int b) {
        return a + b;
    }
}
