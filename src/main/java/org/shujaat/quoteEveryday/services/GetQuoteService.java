package org.shujaat.quoteEveryday.services;

import java.util.Collections;
import java.util.List;

import org.shujaat.quoteEveryday.dto.QuoteDto;
import org.shujaat.quoteEveryday.entity.Quote;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class GetQuoteService {

    private final WebClient webClient;

    public GetQuoteService(WebClient webClient) {
        this.webClient = webClient;
    }

    // this mehtod hits the random quoe api and gets a quote and auhor name;
    public Mono<QuoteDto> getQuote() {
        return webClient
                .get()
                .uri("https://zenquotes.io/api/random")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Quote>>() {
                })
                .onErrorResume(e -> {
                    Quote fallback = new Quote();
                    fallback.setQuote("Every day is a new beginning . Take a deep breath and start again.");
                    fallback.setAuthor("Shujaat Nazir : ");
                    return Mono.just(Collections.singletonList(fallback));
                })
                .map(q -> new QuoteDto(
                        q.get(0).getQuote(),
                        q.get(0).getAuthor()));
    }

}
