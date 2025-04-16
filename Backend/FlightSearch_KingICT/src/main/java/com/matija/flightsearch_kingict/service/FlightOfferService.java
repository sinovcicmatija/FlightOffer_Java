package com.matija.flightsearch_kingict.service;

import com.matija.flightsearch_kingict.model.dto.FlightOfferDTO;
import com.matija.flightsearch_kingict.model.external.FlightOfferCallModel;

import java.util.List;

public interface FlightOfferService {

    public List<FlightOfferDTO> getFlightOffer(FlightOfferCallModel callModel);
}
