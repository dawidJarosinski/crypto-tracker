package org.example.cryptotracker.api;


import org.example.cryptotracker.model.CryptoRequest;
import org.example.cryptotracker.model.CryptoResponse;
import org.example.cryptotracker.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CryptoController {

    private final CryptoService cryptoService;
    @Autowired
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @GetMapping("/cryptos")
    public ResponseEntity<List<CryptoResponse>> getAllCryptos() {
        return ResponseEntity.ok(cryptoService.getAllCryptos());
    }

    @PostMapping("/cryptos")
    public ResponseEntity<CryptoResponse> saveCrypto(@RequestBody CryptoRequest cryptoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cryptoService.createCrypto(cryptoRequest));
    }

    @DeleteMapping("/cryptos/{id}")
    public void deleteCrypto(@PathVariable Integer id) {
        cryptoService.deleteCrypto(id);
    }
}
