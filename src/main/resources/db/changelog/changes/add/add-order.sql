-- Mock orders (with id=1, id=2) for client's history:
INSERT INTO orders (owner_id, master_id, motorcycle_id, description, open_order, status, total_amount, completion_order)
VALUES ( 1, 1, 1, 'tire rip', '2022-03-15 14:40:20.000155', 'PAID', '300', '2022-03-16 14:40:20.000155');
INSERT INTO orders (owner_id, master_id, motorcycle_id, description, open_order, status, total_amount, completion_order)
VALUES ( 1, 1, 1, 'problems with head light', '2022-05-15 14:40:20.000155', 'PAID', '400', '2022-05-16 14:40:20.000155');

-- Testing order
INSERT INTO orders (owner_id, master_id, motorcycle_id, description, open_order, status)
VALUES ( 1, 1, 1, 'TO N2 (20_000 km) / problems with gearbox, its working with noise, hard to switch to lowered transmission',
        '2022-09-29 13:50:25.000160', 'RECEIVED');
