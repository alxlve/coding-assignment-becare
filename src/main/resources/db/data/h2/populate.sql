;
-- 'script' must not be null or empty

-- TRUNCATE TABLES
-- Alphabetical order

SET referential_integrity = FALSE;

TRUNCATE TABLE session;
TRUNCATE TABLE session_measures;
TRUNCATE TABLE user;

SET referential_integrity = TRUE;
