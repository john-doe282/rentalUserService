package com.andrew.rental.service.impl;

import com.andrew.rental.model.BankAccount;
import com.andrew.rental.service.BankAccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    @Override
    public List<BankAccount> getBankAccountByUserId(UUID userId) {
        return null;
    }

    @Override
    public void addBankAccount(BankAccount bankAccount) {

    }
}
