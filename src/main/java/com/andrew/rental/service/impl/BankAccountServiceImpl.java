package com.andrew.rental.service.impl;

import com.andrew.rental.model.BankAccount;
import com.andrew.rental.service.BankAccountService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {
    private final String baseUrl = "http://localhost:8010/bank";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<BankAccount> getBankAccountByUserId(UUID userId) {
        String requestUrl = baseUrl + "/" + userId.toString();
        return restTemplate.getForObject(requestUrl, List.class);
    }
}
