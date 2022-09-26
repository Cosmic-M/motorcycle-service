--liquibase formatted sql
CREATE TABLE IF NOT EXISTS favors
(
    id bigint NOT NULL,
    order_id bigint NOT NULL,
    master_id bigint NOT NULL,
    cost decimal(14, 5) NOT NULL,
    status character varying(256) NOT NULL,
    CONSTRAINT vafor_pk PRIMARY KEY (id)
    CONSTRAINT `favor_order_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `motorcycle_service`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    CONSTRAINT `favor_master_fk`
    FOREIGN KEY (`master_id`)
    REFERENCES `motorcycle_service`.`masters` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

--rollback DROP TABLE favors;
