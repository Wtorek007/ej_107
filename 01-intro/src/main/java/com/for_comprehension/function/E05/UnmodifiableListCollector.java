package com.for_comprehension.function.E05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class UnmodifiableListCollector<T> implements Collector<T, ArrayList<T>, List<T>> {

    static <T> Collector<T, ?, List<T>> toUnmodifiableList() {
        return new UnmodifiableListCollector<>();
    }

    /**
     * Throws {@link UnsupportedOperationException} since {@link Collectors#toCollection(Supplier)}
     * assumes that the target is mutable
     */
    public static void main(String[] args) {
        List<Integer> list = Stream.of(42)
          .collect(toUnmodifiableList());

        list.add(42); // powinno rzucić wyjątek
    }

    @Override
    public Supplier<ArrayList<T>> supplier() {
        return () -> new ArrayList<>();
    }

    @Override
    public BiConsumer<ArrayList<T>, T> accumulator() {
        return (ts, e) -> ts.add(e);
    }

    @Override
    public BinaryOperator<ArrayList<T>> combiner() {
        return (acc1, acc2) -> Stream.concat(acc1.stream(), acc2.stream())
          .collect(Collectors.toCollection(ArrayList::new));

//        return (acc1, acc2) -> {
//            ArrayList<T> result = new ArrayList<>();
//            result.addAll(acc1);
//            result.addAll(acc2);
//            return result;
//        };
    }

    @Override
    public Function<ArrayList<T>, List<T>> finisher() {
        return acc -> Collections.unmodifiableList(acc);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptyNavigableSet();
    }
}
