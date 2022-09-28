-- Mock orders for client history:
INSERT INTO orders (owner_id, master_id, motorcycle_id, description, open_order, favor_id, status, total_amount, completion_order)
VALUES ( 1, 1, 1, 'tire rip', '2022-03-15 14:40:20.000155', 1, 'PAID', '500', '2022-03-16 14:40:20.000155');
INSERT INTO orders (owner_id, master_id, motorcycle_id, description, open_order, favor_id, status, total_amount, completion_order)
VALUES ( 1, 1, 1, 'problems with head light', '2022-05-15 14:40:20.000155', 1, 'PAID', '500', '2022-05-16 14:40:20.000155');

-- Testing order
INSERT INTO orders (owner_id, master_id, motorcycle_id, description, )
VALUES ( 1, 1, 1, 'problems with gearbox, its working with noise, hard to switch to lowered transmission');
