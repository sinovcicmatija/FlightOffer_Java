package com.matija.flightsearch_kingict.model.external.sub.model;

import lombok.Data;

@Data
public class CabinRestrictions {
    private String cabin;
    private String coverage;
    private String[] originDestinationIds;
}
