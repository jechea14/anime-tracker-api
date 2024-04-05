package com.example.animetracker.controller;

import com.example.animetracker.model.User;
import com.example.animetracker.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl jpaUserService;

    public UserController(UserServiceImpl jpaUserService) {
        this.jpaUserService = jpaUserService;
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return jpaUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable("id") Integer id) {
        return jpaUserService.getUser(id);
    }

    @PostMapping("")
    public void createUser(@RequestBody User user) {
        jpaUserService.createUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") Integer id, @RequestBody User user) {
        jpaUserService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        jpaUserService.deleteUser(id);
    }
}
