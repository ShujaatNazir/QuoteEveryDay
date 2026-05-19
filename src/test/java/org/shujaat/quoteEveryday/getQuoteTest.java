package org.shujaat.quoteEveryday;

import org.junit.jupiter.api.Test;
import org.shujaat.quoteEveryday.dto.QuoteDto;
import org.shujaat.quoteEveryday.services.GetQuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
public class getQuoteTest {

    @Autowired
    GetQuoteService  getQuoteService;

    @Test
    public void getQuote() {
        Mono<QuoteDto> quoteDto = getQuoteService.getQuote();
        String quote = quoteDto.block().getQuote() + "\n" + quoteDto.block().getAuthor();
        System.out.println(quote);
    }
}
