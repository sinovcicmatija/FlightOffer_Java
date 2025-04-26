package com.matija.flightsearch_kingict.config;

import com.matija.flightsearch_kingict.model.dto.AirportDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class AirportCacheService {

    private final RedisTemplate<String, List<AirportDTO>> redisTemplate;

    public AirportCacheService(@Qualifier("airportDataRedisTemplate") RedisTemplate<String, List<AirportDTO>> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public List<AirportDTO> getCachedAirport(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void cacheAirport(String keyword, List<AirportDTO> airports , Duration ttl) {
        redisTemplate.opsForValue().set(keyword, airports, ttl);
    }
}
