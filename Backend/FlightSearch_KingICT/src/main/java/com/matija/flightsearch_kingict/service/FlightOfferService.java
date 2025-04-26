package com.matija.flightsearch_kingict.service;

import com.matija.flightsearch_kingict.model.dto.FlightOfferCallDTO;
import com.matija.flightsearch_kingict.model.dto.FlightOfferDTO;

import java.util.List;

public interface FlightOfferService {

    public List<FlightOfferDTO> getFlightOffer(FlightOfferCallDTO callModelDTO);
}
