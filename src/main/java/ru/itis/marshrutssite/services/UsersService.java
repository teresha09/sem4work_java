package ru.itis.marshrutssite.services;

import ru.itis.marshrutssite.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    List<User> getAllUsers();
    Optional<User> check(String token);
}
