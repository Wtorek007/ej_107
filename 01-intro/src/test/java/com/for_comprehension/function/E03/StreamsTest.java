package com.for_comprehension.function.E03;

import com.for_comprehension.function.E03.Streams;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class StreamsTest {

    @Test
    public void l1_upperCaseAll() {

        // given
        final List<String> input = asList("Linus", "Grzegorz", "Vladimir");

        // when
        final List<String> result = Streams.L1_upperCaseAll().apply(input);

        // then
        assertThat(result).containsExactly("LINUS", "GRZEGORZ", "VLADIMIR");
    }

    @Test
    public void l2_upperCaseAllAndFilter() {

        // given
        final List<String> input = asList("Linus", "Grzegorz", "Vladimir");

        // when
        final List<String> result = Streams.L2_upperCaseAllAndFilter().apply(input);

        // then
        assertThat(result).containsExactly("GRZEGORZ", "VLADIMIR");
    }

    @Test
    public void l3_findTheLongestName() {

        // given
        final List<String> input = asList("Linus", "Grzegorz", "Vlad");

        // when
        final String result = Streams.L3_findTheLongestName().apply(input);

        // then
        assertThat(result).isEqualTo("Grzegorz");
    }

    @Test
    public void l4_flatten() {

        // given
        final List<List<Integer>> input = asList(asList(1, 2), asList(3, 4), asList(5, 6));

        // when
        final List<Integer> result = Streams.L4_flatten().apply(input);

        // then
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @Test
    public void l5_distinctElements() {
        // given
        final List<Integer> input = asList(1, 2, 2, 3, 3, 3, 5, 5, 5, 6, 4);

        // when
        final List<Integer> result = Streams.L5_distinctElements().apply(input);

        // then
        assertThat(result).doesNotHaveDuplicates();
    }

    @Test
    public void l6_duplicateElements() {

        // given
        final List<Integer> input = asList(1, 2, 3, 4);

        // when
        final List<Integer> result = Streams.L6_duplicateElements().apply(input);

        // then
        assertThat(result).containsExactly(1, 1, 2, 2, 3, 3, 4, 4);
    }

    @Test
    public void l7_duplicateElementsNTimes() {

        // given
        final List<Integer> input = asList(1, 2, 3, 4);
        final int givenNumberOfTimes = 3;

        // when
        final List<Integer> result = Streams.L7_duplicateElementsNTimes(givenNumberOfTimes).apply(input);

        // then
        assertThat(result).containsExactly(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4);
    }

    @Test
    public void l8_generate3s() {
        // when
        final List<Integer> result = Streams.L8_generate3s().get();

        // then
        assertThat(result).containsExactly(0, 3, 6, 9, 12, 15, 18, 21, 24, 27);
    }

    /**
     * Find five consecutive leap years since 2000
     * {@link Stream#iterate(Object, UnaryOperator)}
     * {@link LocalDate#isLeapYear()}
     */
    @Test
    public void l9_leapYears() {
        // when
        List<Integer> result = Streams.L9_leapYears().get();

        // then
        assertThat(result).containsExactly(2000, 2004, 2008, 2012, 2016);
    }

    @Test
    public void l10_rotate() {

        // given
        final List<Integer> input = asList(1, 2, 3, 4);
        final int n = 3;

        // when
        final List<Integer> result = Streams.L10_rotate(n).apply(input);

        // then
        assertThat(result).containsExactly(4, 1, 2, 3);
    }

    @Test
    public void l11_sum() {
        // given
        final List<Double> input = asList(90d, 9d, 1d);

        // when
        boolean result = Streams.L11_sum().test(input);

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void l12_filterPresent() {
        // given
        final List<Optional<Integer>> list = asList(Optional.of(1), Optional.empty(), Optional.of(2));

        // when
        List<Integer> result = Streams.L12_filterPresent().apply(list);

        // then
        assertThat(result).containsExactly(1, 2);
    }
}