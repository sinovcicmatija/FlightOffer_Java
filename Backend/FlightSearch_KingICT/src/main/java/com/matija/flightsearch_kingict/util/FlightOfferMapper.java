package com.matija.flightsearch_kingict.util;

import com.matija.flightsearch_kingict.model.domain.FlightOfferResponse;
import com.matija.flightsearch_kingict.model.domain.sub.model.FlightData;
import com.matija.flightsearch_kingict.model.dto.FlightOfferDTO;

import java.util.ArrayList;
import java.util.List;

public class FlightOfferMapper {

    public static List<FlightOfferDTO> toDTO(FlightOfferResponse response, int passengerCount) {
        List<FlightOfferDTO> dtoList = new ArrayList<>();

        for(FlightData flightData : response.getData()) {
            boolean isRoundTrip = flightData.getItineraries().length == 2;
            FlightOfferDTO dto = new FlightOfferDTO();

            dto.setDepartureAirport(
                    flightData.getItineraries()[0].getSegments()[0].getDeparture().getIataCode()
            );
            dto.setArrivalAirport(
                    flightData.getItineraries()[0]
                            .getSegments()[flightData.getItineraries()[flightData.getItineraries().length - 1].getSegments().length -1]
                            .getArrival().getIataCode()
            );
            dto.setDepartureDate(
                    flightData.getItineraries()[0].getSegments()[0].getDeparture().getAt()
            );
            if(isRoundTrip) {
                dto.setReturnDate(
                        flightData.getItineraries()[1]
                                .getSegments()[flightData.getItineraries()[flightData.getItineraries().length -1].getSegments().length -1]
                                .getArrival().getAt()
                );
            }
            dto.setNumberOfTransfersDeparture(
                    flightData.getItineraries()[0].getSegments().length - 1
            );
            if(isRoundTrip) {
                dto.setNumberOfTransfersReturn(
                        flightData.getItineraries()[1].getSegments().length - 1
                );
            }
            dto.setPassengerCount(passengerCount);
            dto.setCurrency(flightData.getPrice().getCurrency());
            dto.setTotalPrice(flightData.getPrice().getGrandTotal());
        }
        return dtoList;
    }
}
