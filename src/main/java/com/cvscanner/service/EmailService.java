package com.cvscanner.service;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendJobCompletionEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("hr@example.com");
        message.setSubject("CV Batch Job Completed");
        message.setText(
                "CV processing completed successfully."
        );
        mailSender.send(message);
    }
}