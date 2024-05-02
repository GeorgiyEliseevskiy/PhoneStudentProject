ALTER TABLE IF EXISTS balance
ALTER COLUMN phone_id TYPE bigint;

ALTER TABLE IF EXISTS balance_operations
ALTER COLUMN operation_date TYPE timestamp;