package com.andrew.rental.service.impl;

import com.andrew.rental.model.BankAccount;
import com.andrew.rental.model.Role;
import com.andrew.rental.model.User;
import com.andrew.rental.repository.UserRepository;
import com.andrew.rental.service.BankAccountService;
import com.andrew.rental.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllClients() {
        return userRepository.findAllByRole(Role.CLIENT);
    }

    @Override
    public List<User> findAllOwners() {
        return userRepository.findAllByRole(Role.OWNER);
    }

    @Override
    public User getUserById(UUID id) throws NotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        }

        throw new NotFoundException("User does not exist");
    }

    @Override
    public void deleteUserById(UUID id) throws NotFoundException {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User does not exist");
        }

        userRepository.deleteById(id);
    }


    @Override
    public BankAccount getUserBankAccountById(UUID id) throws NotFoundException {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User does not exist");
        }

        List<BankAccount> bankAccounts = bankAccountService.getBankAccountByUserId(id);

        if (bankAccounts.isEmpty()) {
            throw new NotFoundException("User has not specified a bank account");
        }
        return bankAccounts.get(0);

    }
}

