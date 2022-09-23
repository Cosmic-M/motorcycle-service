--liquibase formatted sql
CREATE TABLE IF NOT EXISTS orders
(
    id bigint NOT NULL,
    motorcycle_id bigint NOT NULL,
    description character varying(256) NOT NULL,
    open_order timestamp NOT NULL,
    moto_service_id bigint NOT NULL,
    moto_part_id bigint NOT NULL,
    status character varying(256) NOT NULL,
    total_amount decimal(14, 5) NOT NULL,
    completion_order timestamp NOT NULL,
    CONSTRAINT order_pk PRIMARY KEY (id)
    CONSTRAINT `order_motorcycle_fk`
    FOREIGN KEY (`motorcycle_id`)
    REFERENCES `motorcycle_service`.`motorcycles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    CONSTRAINT `order_moto_service_fk`
    FOREIGN KEY (`moto_service_id`)
    REFERENCES `motorcycle_service`.`moto_services` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    CONSTRAINT `order_moto_part_fk`
    FOREIGN KEY (`moto_part_id`)
    REFERENCES `motorcycle_service`.`moto_parts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

--rollback DROP TABLE orders;
