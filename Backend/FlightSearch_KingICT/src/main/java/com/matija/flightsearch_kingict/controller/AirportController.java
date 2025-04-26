package com.matija.flightsearch_kingict.controller;

import com.matija.flightsearch_kingict.model.dto.AirportDTO;
import com.matija.flightsearch_kingict.service.AirportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/airports")
    public List<AirportDTO> getAirport(@RequestParam String keyword) {
        return airportService.getAirport(keyword);
    }
}
