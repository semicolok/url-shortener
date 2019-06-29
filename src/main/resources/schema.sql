CREATE TABLE IF NOT EXISTS `SHORT_URL_KEY`
(
    `URL_KEY` BIGINT(20) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`URL_KEY`)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `SHORT_URL`
(
    `SHORT_URL`      VARCHAR(100) NOT NULL,
    `ORIGINAL_URL`   CLOB NOT NULL,
    `CREATED_AT`     TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`SHORT_URL`)
) DEFAULT CHARSET=utf8;
