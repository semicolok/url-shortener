package com.jake.url.shortener.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenerateShortUrlRequestDto {

    @URL
    @NotBlank(message = "'url' must not be empty.")
    private String url;
}
