package org.shujaat.quoteEveryday.services;

import java.time.Duration;
import java.util.List;

import org.shujaat.quoteEveryday.dto.QuoteDto;
import org.shujaat.quoteEveryday.entity.Quote;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class GetQuoteService {

    private final WebClient webClient;

    public GetQuoteService(WebClient webClient) {
        this.webClient = webClient;
    }

    public QuoteDto getQuote() {
        return webClient
                .get()
                .uri("https://zenquotes.io/api/random")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Quote>>() {
                })
                .timeout(Duration.ofSeconds(5))
                .map(q -> new QuoteDto(
                        q.get(0).getQuote(),
                        q.get(0).getAuthor()))
                .block();
    }

}
