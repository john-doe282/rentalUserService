package com.andrew.rental.service;

import com.andrew.rental.model.BankAccount;

import java.util.List;
import java.util.UUID;

public interface BankAccountService {
    List<BankAccount> getBankAccountByUserId(UUID userId);
}
