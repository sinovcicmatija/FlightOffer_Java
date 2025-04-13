package com.matija.flightsearch_kingict.service;

import com.matija.flightsearch_kingict.model.domain.TokenResponse;
import com.matija.flightsearch_kingict.model.external.FlightOfferCallModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class APIService {
    @Value("{amadeus.api.key}")
    private String APIKey;
    @Value("{amadeus.api.secret}")
    private String APISecret;

    @Value("${amadeus.api.flight-offers-url}")
    private String flightOffersUrl;

    @Value("${amadeus.api.token-url}")
    private String tokenUrl;
    private final WebClient webClient;

    public APIService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<TokenResponse> getToken() {
        return webClient.post()
                .uri(tokenUrl)
                .header("Content-Type: application/x-www-form-urlencoded")
                .body(BodyInserters.fromFormData("grant_type", "client_credentials")
                        .with("client_id", APIKey)
                        .with("client_secret", APISecret))
                .retrieve()
                .bodyToMono(TokenResponse.class);
    }

    public Mono<String> getFlightOffer(String token, FlightOfferCallModel flightOfferCallModel) {
        return webClient.post()
                .uri(flightOffersUrl)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/vnd.amadeus+json")
                .bodyValue(flightOfferCallModel)
                .retrieve()
                .bodyToMono(String.class);
    }






}
