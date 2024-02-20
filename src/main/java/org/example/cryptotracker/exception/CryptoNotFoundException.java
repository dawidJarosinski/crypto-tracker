package org.example.cryptotracker.exception;

public class CryptoNotFoundException extends RuntimeException{
    public CryptoNotFoundException() {
        super("Cant find crypto with this symbol!");
    }
}
