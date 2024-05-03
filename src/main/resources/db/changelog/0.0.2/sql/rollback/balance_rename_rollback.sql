ALTER TABLE IF EXISTS balance
ALTER COLUMN phone_id TYPE bigint;

ALTER TABLE IF EXISTS balance_operations
ALTER COLUMN operation_date TYPE timestamp;

ALTER TABLE IF EXISTS balance
ALTER COLUMN create_dttm TYPE timestamptz(100);

ALTER TABLE IF EXISTS balance
ALTER COLUMN modify_dttm TYPE timestamptz(100);

ALTER TABLE IF EXISTS balance_operations
ALTER COLUMN create_dttm TYPE timestamptz(100);

ALTER TABLE IF EXISTS balance_operations
ALTER COLUMN modify_dttm TYPE timestamptz(100);