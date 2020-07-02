package com.for_comprehension.function.E04;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CollectorsExercisesTest {

    @Test
    public void l1_toList() {
        // when
        final List<String> result = CollectorsExercises.L1_toList().apply(asList("foo"));

        // then
        assertThat(result)
          .isInstanceOf(List.class)
          .containsExactly("foo");
    }

    @Test
    public void l2_toLinkedList() {
        // when
        final List<String> result = CollectorsExercises.L2_toLinkedList().apply(asList("foo"));

        // then
        assertThat(result)
          .isInstanceOf(LinkedList.class)
          .containsExactly("foo");
    }

    @Test
    public void l3_unmodifiable() {
        // when
        final List<String> result = CollectorsExercises.L3_unmodifiable().apply(singletonList("foo"));

        // then
        assertThatThrownBy(() -> result.add(""))
          .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void l4_toMap() {

        // when
        final Map<String, Integer> result = CollectorsExercises.L4_toMap().apply(asList("aa", "b") );

        // then
        assertThat(result)
          .containsEntry("AA", 2)
          .containsEntry("B", 1);
    }

    @Test
    public void l5_toTreeMap() {
        // when
        final Map<String, Integer> result = CollectorsExercises.L5_toTreeMap().apply(asList("42", "42"));

        // then
        assertThat(result)
          .isInstanceOf(TreeMap.class)
          .containsEntry("42", 2);
    }

    @Test
    public void l6_toJson() {
        // given
        final Map<String, String> input = new HashMap<String, String>() {{
            put("name", "Linus");
            put("age", "42");
        }};

        // given
        String result = CollectorsExercises.L6_toJson().apply(input);

        System.out.println(result);

        // then
        assertThat(result).isEqualTo("{\"name\":\"Linus\",\"age\":\"42\"}");
    }

    @Test
    public void l7_groupStrings() {
        // given
        List<String> input = asList("Java", "Haskell", "Perl", "Scala");

        // given
        Map<Integer, List<String>> result = CollectorsExercises.L7_groupStrings().apply(input);

        // then
        assertThat(result)
          .containsEntry(4, asList("Java", "Perl"))
          .containsEntry(7, asList("Haskell"))
          .containsEntry(5, asList("Scala"));
    }

    @Test
    public void l8_groupStrings() {
        // given
        List<String> input = asList("Java", "Haskell", "Perl", "Scala");

        // given
        Map<Integer, List<String>> result = CollectorsExercises.L8_groupStrings().apply(input);

        // then
        assertThat(result)
          .isInstanceOf(TreeMap.class)
          .containsEntry(4, asList("Java", "Perl"))
          .containsEntry(7, asList("Haskell"))
          .containsEntry(5, asList("Scala"));
    }

    @Test
    public void l9_groupStrings() {
        // given
        List<String> input = asList("Java", "Haskell", "Perl", "Scala");

        // given
        Map<Integer, String> result = CollectorsExercises.L9_groupStrings().apply(input);

        // then
        assertThat(result)
          .containsEntry(4, "Java,Perl")
          .containsEntry(7, "Haskell")
          .containsEntry(5, "Scala");
    }
}