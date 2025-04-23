package com.matija.flightsearch_kingict.service.impl;

import com.matija.flightsearch_kingict.amadeus.client.AmadeusClient;
import com.matija.flightsearch_kingict.config.FlightOfferCacheService;
import com.matija.flightsearch_kingict.config.TokenService;
import com.matija.flightsearch_kingict.model.domain.FlightOfferResponse;
import com.matija.flightsearch_kingict.model.dto.FlightOfferCallDTO;
import com.matija.flightsearch_kingict.model.dto.FlightOfferDTO;
import com.matija.flightsearch_kingict.model.external.FlightOfferCallModel;
import com.matija.flightsearch_kingict.service.FlightOfferService;
import com.matija.flightsearch_kingict.util.CacheKeyGenerator;
import com.matija.flightsearch_kingict.util.FlightOfferCallMapper;
import com.matija.flightsearch_kingict.util.FlightOfferMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Service
public class FlightOfferServiceImpl implements FlightOfferService {

    private final AmadeusClient client;
    private final TokenService tokenService;
    private final FlightOfferCacheService flightCacheService;

    public FlightOfferServiceImpl(AmadeusClient client, TokenService tokenService, FlightOfferCacheService flightCacheService) {
        this.client = client;
        this.tokenService = tokenService;
        this.flightCacheService = flightCacheService;
    }

    @Override
    public List<FlightOfferDTO> getFlightOffer(FlightOfferCallDTO callModelDTO) {
        String token = tokenService.getAccessToken();
        String hashKey = CacheKeyGenerator.generateHashKey(callModelDTO);

        List<FlightOfferDTO> cachedOffers = flightCacheService.getCachedOffers(hashKey);
        if (cachedOffers != null) {
            System.out.println("Vraćam iz cache-a za ključ: " + hashKey);
            return cachedOffers;
        }
        System.out.println("Podaci nisu isti kao keshirani, dohvacam nove...");

        FlightOfferCallModel callModel = FlightOfferCallMapper.MapToFlightOfferCallModel(callModelDTO);
        FlightOfferResponse response = client.getFlightOffer(token, callModel).block();
        List<FlightOfferDTO> offerDTOs = FlightOfferMapper.toDTO(response);
        flightCacheService.cacheOffers(hashKey, offerDTOs, Duration.ofMinutes(10));

        return offerDTOs;
    }
}
