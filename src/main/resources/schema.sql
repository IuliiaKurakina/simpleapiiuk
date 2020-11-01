DROP TABLE IF EXISTS client CASCADE;
DROP SEQUENCE IF EXISTS global_seq;
CREATE SEQUENCE global_seq START WITH 1;
CREATE TABLE client(
                       id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
                       serial       INTEGER         NOT NULL,
                       number       VARCHAR         NOT NULL,
                       surname        VARCHAR         NOT NULL,
                       name        VARCHAR         NOT NULL,
                       phone    VARCHAR         NOT NULL
);