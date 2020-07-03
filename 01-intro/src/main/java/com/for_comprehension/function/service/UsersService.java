package com.for_comprehension.function.service;

import java.util.Optional;

class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    Optional<String> getUser(int id) {
        return usersRepository.getUserById(id);
    }
}
