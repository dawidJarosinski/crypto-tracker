package org.example.cryptotracker.exception;

public class CantDownloadDataFromApiException extends RuntimeException{
    public CantDownloadDataFromApiException() {
        super("Cant download data from api!");
    }
}
