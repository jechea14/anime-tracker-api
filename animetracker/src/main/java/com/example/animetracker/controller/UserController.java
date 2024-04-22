package com.example.animetracker.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

//    private final UserServiceImpl jpaUserService;

//    public UserController(UserServiceImpl jpaUserService) {
//        this.jpaUserService = jpaUserService;
//    }

    @GetMapping("/yuh")
    public String helloUserController() {
        return "User access level";
    }

//    @GetMapping("")
//    public List<ApplicationUser> getAllUsers() {
//        return jpaUserService.getAllUsers();
//    }
//
//    @GetMapping("/{id}")
//    public Optional<ApplicationUser> getUser(@PathVariable("id") Integer id) {
//        return jpaUserService.getUser(id);
//    }
//
//    @PostMapping("")
//    public void createUser(@RequestBody ApplicationUser applicationUser) {
//        jpaUserService.createUser(applicationUser);
//    }
//
//    @PutMapping("/{id}")
//    public void updateUser(@PathVariable("id") Integer id, @RequestBody ApplicationUser applicationUser) {
//        jpaUserService.updateUser(id, applicationUser);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable("id") Integer id) {
//        jpaUserService.deleteUser(id);
//    }
}
