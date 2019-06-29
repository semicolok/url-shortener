package com.jake.url.shortener.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenerateShortUrlResponseDto {
    private String shortUrl;
    private String originalUrl;
}
