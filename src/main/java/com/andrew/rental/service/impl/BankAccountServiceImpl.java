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
    private String baseUrl = System.getenv("BANK_URL") + ":8081/bank";
    private final RestTemplate restTemplate = new RestTemplate();

    public BankAccountServiceImpl() {
        String host = System.getenv("BANK_URL");
        if (!host.startsWith("http://")) {
            baseUrl = "http://" + host + ":8081/bank";
        }
    }
    @Override
    public List<BankAccount> getBankAccountByUserId(UUID userId) {
        String requestUrl = baseUrl + "/" + userId.toString();
        return restTemplate.getForObject(requestUrl, List.class);
    }
}
