package com.for_comprehension.function.E02;

import java.time.LocalDate;
import java.time.Month;

class OptionalsRefactor {

    private Person findPerson(int id) {
        switch(id) {
            case 1:
                return new Person("James",48, 193, LocalDate.of(2000, Month.NOVEMBER, 1));
            case 2:
                return new Person("John", 62, 169, LocalDate.of(1989, Month.OCTOBER, 21));
            case 0:
                return null;
            default:
                return null;
        }
    }

    private String findAddress(Person person) {
        if (person.getBirthDate().isAfter(LocalDate.of(2000, Month.JANUARY, 1))) {
            return "";
        }
        if (person.getBirthDate().isAfter(LocalDate.of(1980, Month.JANUARY, 1))) {
            return " Some St.   ";
        }
        return null;
    }

    private String findAddressById(int id) {
        final Person personOrNull = findPerson(id);
        if (personOrNull != null) {
            if (personOrNull.getHeight() > 168) {
                final String addressOrNull = findAddress(personOrNull);
                if (addressOrNull != null && !addressOrNull.isEmpty()) {
                    return addressOrNull.trim();
                } else {
                    return null;
                }
            }
        }
        return null;
    }


    // ***
    // DON"T CHANGE ANYTHING BEYOND THIS POINT
    // ***

    private class Person {
        private final String name;
        private final int weight;
        private final int height;
        private final LocalDate birthDate;

        private Person(String name, int weight, int height, LocalDate birthDate) {
            this.name = name;
            this.weight = weight;
            this.height = height;
            this.birthDate = birthDate;
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        public int getHeight() {
            return height;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }
    }
}