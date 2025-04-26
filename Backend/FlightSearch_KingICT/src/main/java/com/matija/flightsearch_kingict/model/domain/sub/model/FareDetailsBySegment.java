package com.matija.flightsearch_kingict.model.domain.sub.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FareDetailsBySegment {
    private String segmentId;
    private String cabin;
    private String fareBasis;
    @JsonProperty("class")
    private String classFare;
    private IncludedCheckedBags includedCheckedBags;
}
