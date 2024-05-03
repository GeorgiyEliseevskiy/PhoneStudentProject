ALTER TABLE IF EXISTS balance
ALTER COLUMN phone_id TYPE VARCHAR(20);

ALTER TABLE IF EXISTS balance_operations
ALTER COLUMN operation_date TYPE VARCHAR(100);

ALTER TABLE IF EXISTS balance
ALTER COLUMN create_dttm TYPE timestamp(100);

ALTER TABLE IF EXISTS balance
ALTER COLUMN modify_dttm TYPE timestamp(100);

ALTER TABLE IF EXISTS balance_operations
ALTER COLUMN create_dttm TYPE timestamp(100);

ALTER TABLE IF EXISTS balance_operations
ALTER COLUMN modify_dttm TYPE timestamp(100);