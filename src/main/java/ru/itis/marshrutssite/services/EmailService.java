package ru.itis.marshrutssite.services;

public interface EmailService {
    void sendMail(String subject, String text, String email);
}
