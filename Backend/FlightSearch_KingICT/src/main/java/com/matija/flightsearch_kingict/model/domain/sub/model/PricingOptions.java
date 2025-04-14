package com.matija.flightsearch_kingict.model.domain.sub.model;

import lombok.Data;

@Data
public class PricingOptions {
    private String[] fareType;
    private boolean includedCheckedBagsOnly;
}
