package com.jake.url.shortener.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UrlShortenerException extends RuntimeException {
    private HttpStatus httpStatus;

    public UrlShortenerException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
