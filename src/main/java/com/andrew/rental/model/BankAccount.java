package com.andrew.rental.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class BankAccount {
    @Id
    @GeneratedValue
    private UUID id;

    @NonNull
    private String iban;

    @NonNull
    private int balance;

    @NonNull
    private UUID userId;
}
