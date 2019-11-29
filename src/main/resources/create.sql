CREATE TABLE IF NOT EXISTS user
(
    username VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS vip
(
    id         VARCHAR(20) NOT NULL PRIMARY KEY,
    name       VARCHAR(20) NOT NULL,
    sex        CHAR(1),
    phone      VARCHAR(20),
    address    VARCHAR(100),
    email      VARCHAR(30),
    createTime DATE        NOT NULL,
    status     INT         NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS good
(
    id           VARCHAR(20) NOT NULL PRIMARY KEY,
    name         VARCHAR(20) NOT NULL,
    maker        VARCHAR(20),
    createTime   DATE        NOT NULL,
    price        DECIMAL     NOT NULL,
    discount     DOUBLE,
    remain       LONG        NOT NULL DEFAULT 0,
    introduction TEXT,
    remarks      TEXT,
    status       INT         NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS record
(
    id         VARCHAR(20) NOT NULL PRIMARY KEY,
    vipId      VARCHAR(20) NOT NULL,
    createTime DATE        NOT NULL,
    price      DECIMAL     NOT NULL
);

CREATE TABLE IF NOT EXISTS record_detail
(
    recordId VARCHAR(20) NOT NULL,
    goodId   VARCHAR(20) NOT NULL,
    goodName VARCHAR(20) NOT NULL,
    price    DECIMAL     NOT NULL,
    number   INTEGER     NOT NULL
)