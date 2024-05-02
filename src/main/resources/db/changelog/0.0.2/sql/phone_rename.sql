ALTER TABLE IF EXISTS phone
ALTER COLUMN create_dttm TYPE timestamp(100);

ALTER TABLE IF EXISTS phone
ALTER COLUMN modify_dttm TYPE timestamp(100);