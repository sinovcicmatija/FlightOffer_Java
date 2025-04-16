package com.matija.flightsearch_kingict.service.impl;

import com.matija.flightsearch_kingict.amadeus.client.AmadeusClient;
import com.matija.flightsearch_kingict.model.domain.LocationResponse;
import com.matija.flightsearch_kingict.model.domain.TokenResponse;
import com.matija.flightsearch_kingict.model.dto.AirportDTO;
import com.matija.flightsearch_kingict.service.AirportService;
import com.matija.flightsearch_kingict.util.AirportMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AirportServiceImpl implements AirportService {

    private final AmadeusClient client;

    public AirportServiceImpl(AmadeusClient client) {
        this.client = client;
    }

    @Override
    public List<AirportDTO> getAirport(String keyword) {
        String token = Objects.requireNonNull(client.getToken().block()).getAccess_token();

        LocationResponse response = client.getLocationAirport(token, keyword).block();

        return AirportMapper.toDTO(response);
    }
}
