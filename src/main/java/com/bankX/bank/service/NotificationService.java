package com.bankX.bank.service;

import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    public void sendNotification(String message) {
        // Simulate sending notification
        System.out.println("Notification: " + message);
    }
}
