package org.example.cryptotracker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cryptos")
@Data
@NoArgsConstructor
public class CryptoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crypto_id")
    private Integer id;

    @Column(name = "crypto_symbol")
    private String symbol;

    @Column(name = "crypto_quantity")
    private Double quantity;

    @Column(name = "crypto_price")
    private Float pucharsePrice;

    public CryptoEntity(String symbol, Double quantity, Float pucharsePrice) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.pucharsePrice = pucharsePrice;
    }
}
