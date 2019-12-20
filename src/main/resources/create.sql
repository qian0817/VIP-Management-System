CREATE TABLE IF NOT EXISTS user
(
    id       INTEGER PRIMARY KEY,
    username VARCHAR(20),
    password VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS vip
(
    id         INTEGER PRIMARY KEY,
    userId     INTEGER,
    vipNo      VARCHAR(20),
    name       VARCHAR(20),
    sex        CHAR(1),
    phone      VARCHAR(20),
    address    VARCHAR(100),
    email      VARCHAR(30),
    createTime DATE
);

CREATE TABLE IF NOT EXISTS good
(
    id           INTEGER PRIMARY KEY,
    userId       INTEGER,
    goodNo       VARCHAR(20),
    name         VARCHAR(20),
    maker        VARCHAR(20),
    createTime   DATE,
    price        DECIMAL,
    discount     DECIMAL,
    remain       LONG,
    introduction TEXT,
    remarks      TEXT
);

CREATE TABLE IF NOT EXISTS record
(
    id         INTEGER PRIMARY KEY,
    userId     INTEGER,
    recordNo   VARCHAR(20),
    vipId      VARCHAR(20),
    vipName    VARCHAR(20),
    vipPhone   VARCHAR(20),
    createTime DATE,
    price      DECIMAL
);

CREATE TABLE IF NOT EXISTS record_detail
(
    id       INTEGER PRIMARY KEY,
    userId   INTEGER,
    recordNo VARCHAR(20),
    goodNo   VARCHAR(20),
    goodName VARCHAR(20),
    price    DECIMAL,
    number   INTEGER
)