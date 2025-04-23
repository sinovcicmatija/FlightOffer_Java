package com.matija.flightsearch_kingict.service.impl;

import com.matija.flightsearch_kingict.amadeus.client.AmadeusClient;
import com.matija.flightsearch_kingict.config.TokenService;
import com.matija.flightsearch_kingict.model.domain.FlightOfferResponse;
import com.matija.flightsearch_kingict.model.dto.FlightOfferCallDTO;
import com.matija.flightsearch_kingict.model.dto.FlightOfferDTO;
import com.matija.flightsearch_kingict.model.external.FlightOfferCallModel;
import com.matija.flightsearch_kingict.service.FlightOfferService;
import com.matija.flightsearch_kingict.util.FlightOfferCallMapper;
import com.matija.flightsearch_kingict.util.FlightOfferMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FlightOfferServiceImpl implements FlightOfferService {

    private final AmadeusClient client;
    private final TokenService tokenService;

    public FlightOfferServiceImpl(AmadeusClient client, TokenService tokenService) {
        this.client = client;
        this.tokenService = tokenService;
    }

    @Override
    public List<FlightOfferDTO> getFlightOffer(FlightOfferCallDTO callModelDTO) {
        String token = tokenService.getAccessToken();

        FlightOfferCallModel callModel = FlightOfferCallMapper.MapToFlightOfferCallModel(callModelDTO);

        FlightOfferResponse response = client.getFlightOffer(token, callModel).block();

        return FlightOfferMapper.toDTO(response);
    }
}
