package com.example.animetracker.service.impl;

import com.example.animetracker.model.User;
import com.example.animetracker.repository.UserRepository;
import com.example.animetracker.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier(value = "postgresUserService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void createUser(User user) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        Optional<User> userOptional1 = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent() || userOptional1.isPresent()) {
            throw new EntityNotFoundException("User already exists.");
        }
        userRepository.save(user);
    }

    @Override
    public void updateUser(Integer id, User user) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer id) {
        doesUserExist(id);

        userRepository.deleteById(id);
    }

    private void doesUserExist(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User not found");
        }
    }
}
