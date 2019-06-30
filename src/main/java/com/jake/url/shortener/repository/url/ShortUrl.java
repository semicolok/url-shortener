package com.jake.url.shortener.repository.url;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "SHORT_URL")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShortUrl {

    public static ShortUrl of(String generatedUrl, String originalUrl) {
        Assert.hasText(generatedUrl, "Can not create with empty shortUrl.");
        Assert.hasText(originalUrl, "Can not create with empty originalUrl.");

        final ShortUrl shortUrl = new ShortUrl();
        shortUrl.shortUrl = generatedUrl;
        shortUrl.originalUrl = originalUrl;

        return shortUrl;
    }

    @Id
    @Column(name = "SHORT_URL", nullable = false)
    private String shortUrl;

    @Column(name = "ORIGINAL_URL", nullable = false)
    private String originalUrl;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
