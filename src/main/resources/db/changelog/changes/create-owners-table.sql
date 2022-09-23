--liquibase formatted sql
CREATE TABLE IF NOT EXISTS owners
(
    id bigint NOT NULL,
    motorcycle_id bigint NOT NULL,
    order_id bigint NOT NULL,
    CONSTRAINT owner_pk PRIMARY KEY (id)
    CONSTRAINT `owner_motorcycle_fk`
    FOREIGN KEY (`motorcycle_id`)
    REFERENCES `motorcycle_service`.`motorcycles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    CONSTRAINT `owner_order_fk`
    FOREIGN KEY (`owner_id`)
    REFERENCES `motorcycle_service`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

--rollback DROP TABLE owners;
