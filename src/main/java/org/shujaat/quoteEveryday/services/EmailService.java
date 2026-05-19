package org.shujaat.quoteEveryday.services;

import java.time.Duration;

import org.shujaat.quoteEveryday.dto.QuoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    // value get the value form the application-local file that is storeed on local
    // mahcine
    @Value("${mail.to}")
    private String to;

    @Value("${MAIL_FROM}")
    private String from;

    private final String subject = "Motivational quote of the Day!!";

    private final GetQuoteService getQuoteService;

    public EmailService(GetQuoteService getQuoteService) {
        this.getQuoteService = getQuoteService;
    }

    @Autowired
    private JavaMailSender javaMailSender;

    // this mehtod run every 30s , with the initial delay of 2s..
    @Scheduled(fixedDelay = 30000, initialDelay = 2000)

    // calls qet quote servic and extracts the quote and auothr name , and send the
    // mail;
    public void sendEmail() {
        System.out.println("entered the send mail method");

        QuoteDto quoteDto = getQuoteService.getQuote().timeout(Duration.ofSeconds(7)).block();
        String body = quoteDto.getQuote() + "\nAuthor : " + quoteDto.getAuthor();

        try {

            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setFrom(from);
            mail.setSubject(subject);
            mail.setText(body);

            javaMailSender.send(mail);
            System.out.println("Email Sent");
        } catch (Exception e) {
            System.out.println("Error while sending the mail");
            e.printStackTrace();
        }

    }

}
