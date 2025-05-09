package com.matija.flightsearch_kingict.util;

import com.matija.flightsearch_kingict.model.domain.FlightOfferResponse;
import com.matija.flightsearch_kingict.model.domain.sub.model.FlightData;
import com.matija.flightsearch_kingict.model.dto.FlightOfferDTO;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightOfferMapper {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ISO_DATE_TIME;
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy. 'u' HH:mm");

    private static String formatDateTime(String isoDateTime) {
        return LocalDateTime.parse(isoDateTime, INPUT_FORMAT).format(OUTPUT_FORMAT);
    }

    public static List<FlightOfferDTO> toDTO(FlightOfferResponse response) {
        List<FlightOfferDTO> dtoList = new ArrayList<>();

        for(FlightData flightData : response.getData()) {
            boolean isRoundTrip = flightData.getItineraries().length == 2;
            FlightOfferDTO dto = new FlightOfferDTO();

            dto.setDepartureAirport(
                    flightData.getItineraries()[0].getSegments()[0].getDeparture().getIataCode()
            );
            dto.setArrivalAirport(
                    flightData.getItineraries()[0]
                            .getSegments()[flightData.getItineraries()[0].getSegments().length -1]
                            .getArrival().getIataCode()
            );
            dto.setDepartureDate(
                    formatDateTime(flightData.getItineraries()[0].getSegments()[0].getDeparture().getAt())
            );
            if(isRoundTrip) {
                dto.setReturnDate(
                        formatDateTime(flightData.getItineraries()[1]
                                .getSegments()[flightData.getItineraries()[1].getSegments().length -1]
                                .getArrival().getAt()
                        ));
            }
            dto.setNumberOfTransfersDeparture(
                    flightData.getItineraries()[0].getSegments().length - 1
            );
            if(isRoundTrip) {
                dto.setNumberOfTransfersReturn(
                        flightData.getItineraries()[1].getSegments().length - 1
                );
            }
            dto.setPassengerCount(
                    flightData.getTravelerPricings().length
            );
            dto.setCurrency(flightData.getPrice().getCurrency());
            dto.setTotalPrice(flightData.getPrice().getGrandTotal());

            dtoList.add(dto);
        }
        return dtoList;
    }
}
