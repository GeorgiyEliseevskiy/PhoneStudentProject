ALTER TABLE IF EXISTS phone
ALTER COLUMN create_dttm TYPE timestamptz(100);

ALTER TABLE IF EXISTS phone
ALTER COLUMN modify_dttm TYPE timestamptz(100);