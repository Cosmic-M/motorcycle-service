--liquibase formatted sql
CREATE TABLE IF NOT EXISTS motorcycles
(
    id bigint NOT NULL,
    brand character varying(256) NOT NULL,
    model character varying(256) NOT NULL,
    production_year int NOT NULL,
    license character varying(256) NOT NULL,
    owner_id bigint NOT NULL,
    CONSTRAINT motorcycle_pk PRIMARY KEY (id)
    CONSTRAINT `motorcycle_owner_fk`
    FOREIGN KEY (`owner_id`)
    REFERENCES `motorcycle_service`.`owners` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

--rollback DROP TABLE motorcycles;
