--liquibase formatted sql
CREATE TABLE IF NOT EXISTS moto_parts
(
    id bigint NOT NULL,
    title character varying(256) NOT NULL,
    cost decimal(14, 5) NOT NULL,
    order_id bigint NOT NULL,
    CONSTRAINT moto_part_pk PRIMARY KEY (id)
    );

--rollback DROP TABLE moto_parts;
