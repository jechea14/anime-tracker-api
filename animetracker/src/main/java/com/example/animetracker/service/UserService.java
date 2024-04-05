package com.example.animetracker.service;

import com.example.animetracker.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUser(Integer id);

    void createUser(User user);

    void updateUser(Integer id, User user);

    void deleteUser(Integer id);
}
