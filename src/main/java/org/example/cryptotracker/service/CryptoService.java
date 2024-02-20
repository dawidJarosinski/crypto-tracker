package org.example.cryptotracker.service;

import jakarta.transaction.Transactional;
import org.example.cryptotracker.exception.CantFindCryptoException;
import org.example.cryptotracker.exception.CryptoNotFoundException;
import org.example.cryptotracker.model.CryptoApiDto;
import org.example.cryptotracker.model.CryptoEntity;
import org.example.cryptotracker.model.CryptoRequest;
import org.example.cryptotracker.model.CryptoResponse;
import org.example.cryptotracker.repository.CryptoRepository;
import org.example.cryptotracker.webClient.CryptoApiClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CryptoService {

    private final CryptoApiClient cryptoApiClient;
    private final CryptoRepository cryptoRepository;

    public CryptoService(CryptoApiClient cryptoApiClient,
                         CryptoRepository cryptoRepository) {
        this.cryptoApiClient = cryptoApiClient;
        this.cryptoRepository = cryptoRepository;
    }

    public List<CryptoResponse> getAllCryptos() {

        List<CryptoResponse> cryptos = new ArrayList<>();

        for(CryptoEntity cryptoFromDatabase : cryptoRepository.findAll()) {
            CryptoApiDto cryptoFromApi = cryptoApiClient.findCryptoDataBySymbol(cryptoFromDatabase.getSymbol());
            cryptos.add(buildCryptoResponse(cryptoFromApi, cryptoFromDatabase));

        }
        return cryptos;
    }

    @Transactional
    public CryptoResponse createCrypto(CryptoRequest cryptoRequest) {
        if(!cryptoApiClient.checkIfCryptoWithSymbolExists(cryptoRequest.symbol())) {
            throw new CryptoNotFoundException();
        }
        CryptoEntity crypto = new CryptoEntity(
                cryptoRequest.symbol(),
                cryptoRequest.quantity(),
                cryptoRequest.pucharsePrice());

        cryptoRepository.save(crypto);

        CryptoApiDto cryptoFromApi = cryptoApiClient.findCryptoDataBySymbol(cryptoRequest.symbol());

        return buildCryptoResponse(cryptoFromApi, crypto);
    }

    @Transactional
    public void deleteCrypto(Integer id) {
        Optional<CryptoEntity> cryptoEntity = cryptoRepository.findCryptoEntitiesById(id);
        if(cryptoEntity.isEmpty()) {
            throw new CantFindCryptoException();
        }
        cryptoRepository.delete(cryptoEntity.get());
    }



    private Float countPercentageChange(CryptoApiDto cryptoFromApi,
                                        CryptoEntity cryptoFromDatabase) {
        return ((cryptoFromApi.getCurrentPrice()
                / cryptoFromDatabase.getPucharsePrice()) * 100) - 100;
    }

    private CryptoResponse buildCryptoResponse(CryptoApiDto cryptoFromApi, CryptoEntity cryptoFromDatabase) {
        return CryptoResponse.builder()
                .id(cryptoFromDatabase.getId())
                .name(cryptoFromApi.getName())
                .symbol(cryptoFromDatabase.getSymbol())
                .quantity(cryptoFromDatabase.getQuantity())
                .pucharsePrice(cryptoFromDatabase.getPucharsePrice())
                .actualPrice(cryptoFromApi.getCurrentPrice())
                .percentageChange(countPercentageChange(cryptoFromApi, cryptoFromDatabase))
                .build();
    }

}
