package org.example.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SenderEmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${email.from}")
    private String emailFrom;

    @Value("${email.to}")
    private String emailTo;

    public void sendSimpleEmail(String body,
                                String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(emailTo);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
    }
}
