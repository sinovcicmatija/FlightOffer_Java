package com.matija.flightsearch_kingict.model.domain;

import com.matija.flightsearch_kingict.model.domain.sub.model.Dictionaries;
import com.matija.flightsearch_kingict.model.domain.sub.model.FlightData;
import com.matija.flightsearch_kingict.model.domain.sub.model.Meta;
import lombok.Data;

@Data
public class FlightOfferResponse {
    private Meta meta;
    private FlightData[] data;
    private Dictionaries dictionaries;
}
