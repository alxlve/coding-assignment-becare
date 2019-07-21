;
-- 'script' must not be null or empty

CREATE TABLE user
(
    id         VARCHAR(36)      NOT NULL,
    created_on TIMESTAMP(26, 6) NOT NULL,
    updated_on TIMESTAMP(26, 6) NOT NULL,
    email      VARCHAR(254)     NOT NULL,
    first_name VARCHAR(64)      NOT NULL,
    last_name  VARCHAR(64)      NOT NULL,
    version    BIGINT           NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT user_un_email
        UNIQUE (email);

CREATE TABLE session
(
    id            VARCHAR(36)      NOT NULL,
    created_on    TIMESTAMP(26, 6) NOT NULL,
    updated_on    TIMESTAMP(26, 6) NOT NULL,
    end_date      TIMESTAMP(26, 6) NOT NULL,
    session_state VARCHAR(16)      NOT NULL,
    start_date    TIMESTAMP(26, 6) NOT NULL,
    version       BIGINT           NOT NULL,
    user_id       VARCHAR(36)      NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE session
    ADD CONSTRAINT session_en_session_state
        CHECK session_state IN ('RECEIVED', 'STAGE_1', 'STAGE_2', 'PROCESSED');

ALTER TABLE session
    ADD CONSTRAINT session_fk_user_id
        FOREIGN KEY (user_id) REFERENCES user;

CREATE TABLE session_measures
(
    session_id VARCHAR(36) NOT NULL,
    value      DOUBLE      NOT NULL,
    measure    VARCHAR(64) NOT NULL,

    PRIMARY KEY (session_id, measure)
);

ALTER TABLE session_measures
    ADD CONSTRAINT session_measures_fk_session_id
        FOREIGN KEY (session_id) REFERENCES session;
