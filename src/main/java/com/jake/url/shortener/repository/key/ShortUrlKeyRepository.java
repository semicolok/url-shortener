package com.jake.url.shortener.repository.key;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlKeyRepository extends JpaRepository<ShortUrlKey, Long> {
}
