package org.example.cryptotracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CryptoApiDto {
    private String symbol;
    private String name;
    @JsonProperty("current_price")
    private Float currentPrice;
}
