package com.devpaik.nss.adapter.out.infra.mail;

import com.devpaik.nss.dto.EmailMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SMTPTransporterTest {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SMTPTransporter smtpTransporter;




    @Test
    public void sendTest() {
        EmailMessage emailMessage = new EmailMessage(
                1L,
                "pscheol@gmail.com",
                "pscheol@gmail.com",
                "TEST mail",
                "<html><body><h2>Hello world</h2></body></html>",
                LocalDateTime.now()
        );
        smtpTransporter.sendMail(emailMessage);
    }
}