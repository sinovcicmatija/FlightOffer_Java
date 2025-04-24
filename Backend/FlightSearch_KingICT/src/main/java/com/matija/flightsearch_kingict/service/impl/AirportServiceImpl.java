package com.matija.flightsearch_kingict.service.impl;

import com.matija.flightsearch_kingict.amadeus.client.AmadeusClient;
import com.matija.flightsearch_kingict.config.AirportCacheService;
import com.matija.flightsearch_kingict.config.TokenService;
import com.matija.flightsearch_kingict.model.domain.LocationResponse;
import com.matija.flightsearch_kingict.model.domain.TokenResponse;
import com.matija.flightsearch_kingict.model.dto.AirportDTO;
import com.matija.flightsearch_kingict.service.AirportService;
import com.matija.flightsearch_kingict.util.AirportMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AirportServiceImpl implements AirportService {

    private final AmadeusClient client;
    private final TokenService tokenService;
    private final AirportCacheService airportCacheService;

    public AirportServiceImpl(AmadeusClient client, TokenService tokenService, AirportCacheService airportCacheService) {
        this.client = client;
        this.tokenService = tokenService;
        this.airportCacheService = airportCacheService;
    }

    @Override
    public List<AirportDTO> getAirport(String keyword) {
        String token = tokenService.getAccessToken();
        String normalizedKey = "airports:" + keyword.trim().toLowerCase();
        List<AirportDTO> cachedAirports = airportCacheService.getCachedAirport(normalizedKey);
        if (cachedAirports != null) {
            System.out.println("Vraćam aerodrome iz cachea" + cachedAirports);
            return cachedAirports;
    }
        System.out.println("Podaci nisu isti kao keshirani, dohvacam nove...");

        LocationResponse response = client.getLocationAirport(token, keyword).block();
        List<AirportDTO> airportsDTOs = AirportMapper.toDTO(response);
        airportCacheService.cacheAirport(normalizedKey,airportsDTOs, Duration.ofMinutes(10));

        return airportsDTOs;
    }
}
