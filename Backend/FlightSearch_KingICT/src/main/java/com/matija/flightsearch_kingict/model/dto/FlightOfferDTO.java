package com.matija.flightsearch_kingict.model.dto;

import lombok.Data;

@Data
public class FlightOfferDTO {
    private String departureAirport;
    private String arrivalAirport;
    private String departureDate;
    private String returnDate;
    private int numberOfTransfersDeparture;
    private int numberOfTransfersReturn;
    private int passengerCount;
    private String currency;
    private String totalPrice;
}
