package com.matija.flightsearch_kingict.model.domain;

import com.matija.flightsearch_kingict.model.domain.sub.model.LocationData;
import lombok.Data;

@Data
public class LocationResponse {
    private LocationData locationData;
}
