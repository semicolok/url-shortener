package com.jake.url.shortener.repository.key;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "SHORT_URL_KEY")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShortUrlKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "URL_KEY", nullable = false)
    private Long urlKey;
}
