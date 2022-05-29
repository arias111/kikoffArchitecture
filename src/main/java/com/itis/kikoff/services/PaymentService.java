package com.itis.kikoff.services;

public interface PaymentService {
    void pay(String token);
    int getBalance(String token);
}
