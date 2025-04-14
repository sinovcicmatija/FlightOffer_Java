package com.matija.flightsearch_kingict.util;

import com.matija.flightsearch_kingict.model.domain.LocationResponse;
import com.matija.flightsearch_kingict.model.dto.AirportDTO;

public class AirportMapper {

    public AirportDTO toDTO(LocationResponse locationResponse) {
        AirportDTO dto = new AirportDTO();
        dto.setAirportName(locationResponse.getLocationData().getAddress().getCityName());
        dto.setAirportName(locationResponse.getLocationData().getName());
        dto.setIataCode(locationResponse.getLocationData().getIataCode());
        dto.setCountryCode(locationResponse.getLocationData().getAddress().getCountryCode());

        return dto;
    }
}
