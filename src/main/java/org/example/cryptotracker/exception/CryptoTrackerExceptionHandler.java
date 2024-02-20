package org.example.cryptotracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class CryptoTrackerExceptionHandler {


    @ExceptionHandler(CantDownloadDataFromApiException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cantDownloadDataFromApiHandler(CantDownloadDataFromApiException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(CryptoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cryptoNotFoundHandler(CryptoNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(HttpClientErrorException.TooManyRequests.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    String tooManyRequestsHandler(HttpClientErrorException.TooManyRequests ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(CantFindCryptoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cantFindCryptoHandler(CantFindCryptoException ex) {
        return ex.getMessage();
    }
}
