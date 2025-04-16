package com.matija.flightsearch_kingict.service.impl;

import com.matija.flightsearch_kingict.amadeus.client.AmadeusClient;
import com.matija.flightsearch_kingict.model.domain.FlightOfferResponse;
import com.matija.flightsearch_kingict.model.dto.FlightOfferDTO;
import com.matija.flightsearch_kingict.model.external.FlightOfferCallModel;
import com.matija.flightsearch_kingict.service.FlightOfferService;
import com.matija.flightsearch_kingict.util.FlightOfferMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FlightOfferServiceImpl implements FlightOfferService {

    private final AmadeusClient client;

    public FlightOfferServiceImpl(AmadeusClient client) {
        this.client = client;
    }

    @Override
    public List<FlightOfferDTO> getFlightOffer(FlightOfferCallModel callModel) {
        String token = Objects.requireNonNull(client.getToken().block()).getAccess_token();

        FlightOfferResponse response = client.getFlightOffer(token, callModel).block();

        return FlightOfferMapper.toDTO(response);
    }
}
