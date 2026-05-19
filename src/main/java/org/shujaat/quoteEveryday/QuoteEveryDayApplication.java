package org.shujaat.quoteEveryday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QuoteEveryDayApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuoteEveryDayApplication.class, args);
    }
}
