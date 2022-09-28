--liquibase formatted sql
CREATE TABLE IF NOT EXISTS masters
(
    id bigint NOT NULL,
    first_name character varying(256) NOT NULL,
    last_name character varying(256) NOT NULL,
    order_id bigint NOT NULL,
    CONSTRAINT master_pk PRIMARY KEY (id)
    CONSTRAINT `master_order_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `motorcycle_service`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

--rollback DROP TABLE masters;
