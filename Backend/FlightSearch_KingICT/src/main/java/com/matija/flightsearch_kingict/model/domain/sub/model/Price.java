package com.matija.flightsearch_kingict.model.domain.sub.model;

import lombok.Data;

@Data
public class Price {
    private String currency;
    private String total;
    private String base;
    private Fees[] fees;
    private String grandTotal;
}
