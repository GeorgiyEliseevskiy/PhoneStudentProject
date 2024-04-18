CREATE SEQUENCE IF NOT EXISTS phone_seq START 1;

CREATE TABLE IF NOT EXISTS phone (
    phone_id BIGINT NOT NULL DEFAULT nextval('phone_seq'::regclass) primary key,
    phone_number VARCHAR(11) NOT NULL,
    status VARCHAR(15) NOT NULL,
    region VARCHAR(15) NOT NULL,
    operator VARCHAR(150) NOT NULL,
    create_dttm         TIMESTAMPTZ     DEFAULT NOW(),
    modify_dttm         TIMESTAMPTZ     DEFAULT NOW()
);
CREATE SEQUENCE IF NOT EXISTS balance_seq START 1;

CREATE TABLE IF NOT EXISTS balance (
     id BIGINT NOT NULL DEFAULT nextval('balance_seq'::regclass) primary key,
     phone_id BIGINT NOT NULL,
     balance DECIMAL(19, 3) NOT NULL,
    create_dttm         TIMESTAMPTZ     DEFAULT NOW(),
    modify_dttm         TIMESTAMPTZ     DEFAULT NOW()
    );

CREATE SEQUENCE IF NOT EXISTS balance_operation_seq START 1;

CREATE TABLE IF NOT EXISTS balance_operations (
    id BIGINT NOT NULL DEFAULT nextval('balance_operation_seq'::regclass) primary key,
    balance_id BIGINT NOT NULL,
    operation_date timestamptz DEFAULT NOW(),
    create_dttm         TIMESTAMPTZ     DEFAULT NOW(),
    modify_dttm         TIMESTAMPTZ     DEFAULT NOW()
    );