package org.example.cryptotracker.webClient;

import org.example.cryptotracker.exception.CantDownloadDataFromApiException;
import org.example.cryptotracker.exception.CryptoNotFoundException;
import org.example.cryptotracker.model.CryptoApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CryptoApiClient {

    private final RestTemplate restTemplate;

    private static final String CRYPTO_CURRENCY_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=10&page=1&sparkline=false&locale=en";
    @Autowired
    public CryptoApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private List<CryptoApiDto> getAllCryptos() {
        List<CryptoApiDto> cryptoCurrency = List.of(restTemplate.getForObject(CRYPTO_CURRENCY_URL, CryptoApiDto[].class));
        if(cryptoCurrency.isEmpty()) {
            throw new CantDownloadDataFromApiException();
        }
        return cryptoCurrency;
    }

    public boolean checkIfCryptoWithSymbolExists(String symbol) {
        for(CryptoApiDto cryptoFromApi : getAllCryptos()) {
            if(cryptoFromApi.getSymbol().equals(symbol)) {
                return true;
            }
        }
        return false;
    }
    public CryptoApiDto findCryptoDataBySymbol(String symbol) {
        for(CryptoApiDto cryptoFromApi : getAllCryptos()) {
            if(cryptoFromApi.getSymbol().equals(symbol)) {
                return cryptoFromApi;
            }
        }
        throw new CryptoNotFoundException();
    }
}
