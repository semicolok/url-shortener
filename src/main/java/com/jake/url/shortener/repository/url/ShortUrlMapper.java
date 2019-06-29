package com.jake.url.shortener.repository.url;

import com.jake.url.shortener.controller.dto.GenerateShortUrlResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ShortUrlMapper {

    GenerateShortUrlResponseDto toGenerateShortUrlResponseDto(ShortUrl shortUrl);
}
