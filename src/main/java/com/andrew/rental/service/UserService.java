package com.andrew.rental.service;

import com.andrew.rental.model.BankAccount;
import com.andrew.rental.model.User;
import javassist.NotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserService {
    void addUser(User user);
    List<User> findAll();
    List<User> findAllClients();
    List<User> findAllOwners();
    User getUserById(UUID id) throws NotFoundException;
    void deleteUserById(UUID id) throws NotFoundException;
    BankAccount getUserBankAccountById(UUID id) throws NotFoundException, IllegalAccessException;

}