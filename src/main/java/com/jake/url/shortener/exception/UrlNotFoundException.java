package com.jake.url.shortener.exception;

import org.springframework.http.HttpStatus;

public class UrlNotFoundException extends UrlShortenerException {

    public UrlNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
