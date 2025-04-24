package com.matija.flightsearch_kingict.config;

import com.matija.flightsearch_kingict.amadeus.client.AmadeusClient;
import com.matija.flightsearch_kingict.model.dto.FlightOfferDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class FlightOfferCacheService {

    private final RedisTemplate<String, List<FlightOfferDTO>> redisTemplate;

    public FlightOfferCacheService(@Qualifier("flightOfferRedisTemplate") RedisTemplate<String, List<FlightOfferDTO>> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public List<FlightOfferDTO> getCachedOffers(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void cacheOffers(String key, List<FlightOfferDTO> offers, Duration ttl) {
        redisTemplate.opsForValue().set(key, offers, ttl);
    }
}
