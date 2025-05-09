package com.matija.flightsearch_kingict.config;

import com.matija.flightsearch_kingict.amadeus.client.AmadeusClient;
import com.matija.flightsearch_kingict.model.domain.TokenResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenService {

    private final RedisTemplate<String, String> redisTemplate;
    private final AmadeusClient amadeusClient;


    public TokenService(@Qualifier("redisTokenTemplate") RedisTemplate<String, String> redisTemplate, AmadeusClient amadeusClient)
    {
        this.redisTemplate = redisTemplate;
        this.amadeusClient = amadeusClient;
    }

    public String getAccessToken() {
        String token = redisTemplate.opsForValue().get("access_token");

        if (token != null) {
            return token;
        }

        TokenResponse tokenResponse = amadeusClient.getToken().block();

        if (tokenResponse != null) {
            redisTemplate.opsForValue().set("access_token", tokenResponse.getAccess_token(),
                    Duration.ofSeconds(tokenResponse.getExpires_in()));
            return tokenResponse.getAccess_token();
        }

        throw new IllegalStateException("Ne mogu dobaviti token!");
    }
}
