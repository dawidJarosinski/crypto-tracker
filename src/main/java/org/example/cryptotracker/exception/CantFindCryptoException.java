package org.example.cryptotracker.exception;

public class CantFindCryptoException extends RuntimeException{
    public CantFindCryptoException() {
        super("Cant find this crypto!");
    }
}
