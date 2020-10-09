package com.andrew.rental.controller;

import com.andrew.rental.model.BankAccount;
import com.andrew.rental.model.User;
import com.andrew.rental.service.BankAccountService;
import com.andrew.rental.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.findAll();
    };

    @GetMapping("{id}")
    public User getUser(@PathVariable UUID id) throws NotFoundException {
        return userService.getUserById(id);
    };

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable UUID id) throws NotFoundException {
        userService.deleteUserById(id);
    };

    @PostMapping
    public void createUser(@Validated @RequestBody User user) {
        userService.addUser(user);
    }

}