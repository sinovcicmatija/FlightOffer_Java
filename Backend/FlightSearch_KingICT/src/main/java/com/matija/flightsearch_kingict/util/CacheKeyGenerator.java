package com.matija.flightsearch_kingict.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matija.flightsearch_kingict.model.dto.FlightOfferCallDTO;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class CacheKeyGenerator {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String generateHashKey(FlightOfferCallDTO dto) {
        try {
            String json = mapper.writeValueAsString(dto);
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(json.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Unable to generate hash key from DTO", e);
        }
    }
}
