package com.for_comprehension.function.service;

import java.util.Optional;

interface UsersRepository {
    Optional<String> getUserById(int id);
}
