package com.matija.flightsearch_kingict.service;

import com.matija.flightsearch_kingict.model.domain.LocationResponse;
import com.matija.flightsearch_kingict.model.domain.TokenResponse;
import com.matija.flightsearch_kingict.model.dto.AirportDTO;

import java.util.List;

public interface AirportService {

    public List<AirportDTO> getAirport(String keyword);
}
