package com.matija.flightsearch_kingict.model.external.sub.model;

import lombok.Builder;
import lombok.Data;
@Builder
@Data
public class DepartureDateTimeRange {
    private String date;
    private String time;
}
