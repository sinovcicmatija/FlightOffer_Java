package com.matija.flightsearch_kingict.amadeus.client;

import com.matija.flightsearch_kingict.model.domain.FlightOfferResponse;
import com.matija.flightsearch_kingict.model.domain.LocationResponse;
import com.matija.flightsearch_kingict.model.domain.TokenResponse;
import com.matija.flightsearch_kingict.model.external.FlightOfferCallModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AmadeusClient {
    //@Value("{amadeus.api.key}")
    //private String APIKey;
    //@Value("{amadeus.api.secret}")
    //private String APISecret;

    @Value("${amadeus.api.flight-offers-url}")
    private String flightOffersUrl;

    @Value("${amadeus.api.token-url}")
    private String tokenUrl;

    @Value("${amadeus.api.location-url}")
    private String locationUrl;

    private final WebClient webClient;

    public AmadeusClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<TokenResponse> getToken() {
        String APIKey = "l9zJCQKmKNRvhWpSvps31EdeTSAOdjTQ";
        String APISecret = "AkaDR2d5F1nRTerN";
        return webClient.post()
                .uri(tokenUrl)
                .header("Content-Type: application/x-www-form-urlencoded")
                .body(BodyInserters.fromFormData("grant_type", "client_credentials")
                        .with("client_id", APIKey)
                        .with("client_secret", APISecret))
                .retrieve()
                .bodyToMono(TokenResponse.class);
    }

    public Mono<FlightOfferResponse> getFlightOffer(String token, FlightOfferCallModel flightOfferCallModel) {
        return webClient.post()
                .uri(flightOffersUrl)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/vnd.amadeus+json")
                .bodyValue(flightOfferCallModel)
                .retrieve()
                .bodyToMono(FlightOfferResponse.class);
    }

    public Mono<LocationResponse> getLocationAirport(String token, String keyword) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(locationUrl)
                        .queryParam("subType","CITY,AIRPORT")
                        .queryParam("keyword", keyword)
                        .build()
                )
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/vnd.amadeus+json")
                .retrieve()
                .bodyToMono(LocationResponse.class);
    }








}
