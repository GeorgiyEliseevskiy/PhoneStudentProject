CREATE SEQUENCE IF NOT EXISTS operator_seq START 1;

CREATE TABLE IF NOT EXISTS operator (
    operator_id BIGINT NOT NULL DEFAULT nextval('operator_seq'::regclass) primary key,
    name varchar(300) NOT NULL
);
