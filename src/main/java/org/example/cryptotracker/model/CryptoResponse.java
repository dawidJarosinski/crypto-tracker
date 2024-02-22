package org.example.cryptotracker.model;

import lombok.Builder;

@Builder
public record CryptoResponse(Integer id,
                             String name,
                             String symbol,
                             Double quantity,
                             Float pucharsePrice,
                             Float actualPrice,
                             Double pucharseValue,
                             Double actualValue,
                             Float percentageChange) {
}
