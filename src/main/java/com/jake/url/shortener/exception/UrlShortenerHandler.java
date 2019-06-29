package com.jake.url.shortener.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class UrlShortenerHandler {

    @ExceptionHandler(UrlShortenerException.class)
    public ResponseEntity<Map<String, String>> handleUrlShortenerException(UrlShortenerException e) {
        final HttpStatus httpStatus = e.getHttpStatus();
        final String message = e.getMessage();

        log.error("UrlShortenerException. httpStatus: {}, message: {}", httpStatus, message, e);

        final Map<String, String> responseBody = new HashMap<>();
        responseBody.put("errorMessage", message);

        return new ResponseEntity<>(responseBody, httpStatus);
    }
}
