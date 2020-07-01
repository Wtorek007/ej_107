package com.for_comprehension.function.E02;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.NoSuchElementException;

import static com.for_comprehension.function.E02.Optionals.L1_bruteForceGet;
import static com.for_comprehension.function.E02.Optionals.L2_customException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OptionalsTest {
    private static final Logger log = LoggerFactory.getLogger(OptionalsTest.class);
    private static final String DEFAULT = "DEFAULT";

    @Test
    public void l1_bruteForceGet() {
        assertThat(L1_bruteForceGet().apply(42)).isInstanceOf(Optionals.Person.class);
        assertThatThrownBy(() -> L1_bruteForceGet().apply(17823)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void l2_bruteForceGet() {
        assertThat(L2_customException().apply(42)).isInstanceOf(Optionals.Person.class);
        assertThatThrownBy(() -> L2_customException().apply(17823)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    public void l3_defaultValue() {
        assertThat(Optionals.L3_defaultValue().apply(42))
          .isEqualTo(Optionals.Person.ANDRE.name);

        assertThat(Optionals.L3_defaultValue().apply(423))
          .isEqualTo("DEFAULT");
    }

    @Test
    public void l4_defaultValueMethod() {
        assertThat(Optionals.L4_defaultValueMethodResult().apply(42, () -> {
            sleep(); return "CALCULATED_NAME";
        })).isEqualTo(Optionals.Person.ANDRE.name);

        assertThat(Optionals.L4_defaultValueMethodResult().apply(43, () -> "CALCULATED_NAME"))
          .isEqualTo("CALCULATED_NAME");
    }

    @Test
    public void l5_processValue() {
        assertThat(Optionals.L5_processValue().apply(42))
          .isEqualTo(Optionals.Person.ANDRE.name.toUpperCase());

        assertThat(Optionals.L5_processValue().apply(43))
          .isEqualTo(DEFAULT);
    }

    @Test
    public void l6_processNestedOptionals() {
        assertThat(Optionals.L6_nestedOptionals().apply(42))
          .isEqualTo(Optionals.Person.ANDRE.name.hashCode() % 80);
    }

    private static void sleep() {
        try {
            log.info("calculating...");
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            // ignore shamefully
        }
    }
}