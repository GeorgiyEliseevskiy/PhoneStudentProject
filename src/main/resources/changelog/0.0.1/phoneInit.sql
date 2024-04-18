CREATE SEQUENCE IF NOT EXISTS phone_seq START 1;

CREATE TABLE IF NOT EXISTS phone (
    phone_id BIGINT NOT NULL DEFAULT nextval('phone_seq'::regclass),
    phone_number VARCHAR(11) NOT NULL,



    CONSTRAINT pk_phone PRIMARY KEY (phone_id)
);
