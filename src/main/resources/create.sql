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
    status     INT         NOT NULL
);

CREATE TABLE IF NOT EXISTS good
(
    id           VARCHAR(20) NOT NULL PRIMARY KEY,
    name         VARCHAR(20) NOT NULL,
    maker        VARCHAR(20),
    createTime   DATE,
    price        DECIMAL,
    discount     DOUBLE,
    remain       LONG,
    introduction TEXT,
    remarks      TEXT,
    status       INT
);

CREATE TABLE IF NOT EXISTS record
(
    id         INTEGER     NOT NULL PRIMARY KEY,
    vipId      VARCHAR(20) NOT NULL,
    goodId     VARCHAR(20) NOT NULL,
    createTime DATE        NOT NULL,
    price      DECIMAL     NOT NULL
);