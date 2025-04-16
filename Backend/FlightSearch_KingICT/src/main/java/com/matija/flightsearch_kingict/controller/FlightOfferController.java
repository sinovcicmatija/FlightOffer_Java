package com.matija.flightsearch_kingict.controller;

import com.matija.flightsearch_kingict.model.dto.FlightOfferDTO;
import com.matija.flightsearch_kingict.model.external.FlightOfferCallModel;
import com.matija.flightsearch_kingict.service.FlightOfferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightOfferController {
    private final FlightOfferService flightOfferService;

    public FlightOfferController(FlightOfferService flightOfferService) {
        this.flightOfferService = flightOfferService;
    }

        @PostMapping("/flight-offers")
        public List<FlightOfferDTO> getFlightOffer(@RequestBody FlightOfferCallModel callModel) {
            return flightOfferService.getFlightOffer(callModel);
        }
    }

