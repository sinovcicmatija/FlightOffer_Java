package com.matija.flightsearch_kingict.util;

import com.matija.flightsearch_kingict.model.domain.LocationResponse;
import com.matija.flightsearch_kingict.model.dto.AirportDTO;

import java.util.List;

public class AirportMapper {

    public List<AirportDTO> toDTO(LocationResponse locationResponse) {
        return locationResponse.getLocationData().stream()
                .map(location -> {
                    AirportDTO dto = new AirportDTO();
                    dto.setCityName(location.getAddress().getCityName());
                    dto.setAirportName(location.getName());
                    dto.setIataCode(location.getIataCode());
                    dto.setCountryCode(location.getAddress().getCountryCode());
                    return dto;
                })
                .toList();
    }
}
