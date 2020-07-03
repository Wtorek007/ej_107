package com.for_comprehension.function.service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

class InMemoryUsersRepository implements UsersRepository {

    private final Map<Integer, String> users = new ConcurrentHashMap<>();

    @Override
    public Optional<String> getUserById(int id) {
        return Optional.ofNullable(users.get(id));
    }
}
