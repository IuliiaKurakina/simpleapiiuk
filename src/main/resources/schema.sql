DROP TABLE IF EXISTS client CASCADE;
DROP SEQUENCE IF EXISTS global_seq;
CREATE SEQUENCE global_seq START WITH 1;
CREATE TABLE client
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    passport  VARCHAR NOT NULL,
    surname VARCHAR NOT NULL,
    name    VARCHAR NOT NULL,
    date_of_birth DATE NOT NULL,
    phone   VARCHAR NOT NULL
);

DROP TABLE IF EXISTS deposit CASCADE;
DROP SEQUENCE IF EXISTS global_seq;
CREATE SEQUENCE global_seq START WITH 1;
CREATE TABLE deposit
(
    deposit_id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    interest_rate   INTEGER NOT NULL,
    term_of_deposit INTEGER NOT NULL,
    prolongation    BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS account CASCADE;
DROP SEQUENCE IF EXISTS global_seq;
CREATE SEQUENCE global_seq START WITH 1;
CREATE TABLE account
(
    accounts_id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    client_id   INTEGER REFERENCES  client (id),
    deposit_id INTEGER   REFERENCES deposit (id),
    date_closing DATE NOT NULL,
    amount INTEGER NOT NULL
);
