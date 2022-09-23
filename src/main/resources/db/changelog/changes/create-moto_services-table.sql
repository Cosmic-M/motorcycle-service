--liquibase formatted sql
CREATE TABLE IF NOT EXISTS moto_services
(
    id bigint NOT NULL,
    order_id bigint NOT NULL,
    master_id bigint NOT NULL,
    total_cost decimal(14, 5) NOT NULL,
    status character varying(256) NOT NULL,
    CONSTRAINT moto_service_pk PRIMARY KEY (id)
    CONSTRAINT `moto_service_order_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `motorcycle_service`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    CONSTRAINT `moto_service_master_fk`
    FOREIGN KEY (`master_id`)
    REFERENCES `motorcycle_service`.`masters` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

--rollback DROP TABLE moto_services;
