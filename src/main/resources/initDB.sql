-- public.consumers definition

CREATE TABLE public.consumers (
                                  id bigserial NOT NULL,
                                  isdeleted bool NULL,
                                  "name" varchar(255) NULL,
                                  CONSTRAINT consumers_pkey PRIMARY KEY (id)
);

-- public.products definition

CREATE TABLE public.products (
                                 id bigserial NOT NULL,
                                 isdeleted bool NULL,
                                 price int4 NULL,
                                 title varchar(255) NULL,
                                 CONSTRAINT products_pkey PRIMARY KEY (id)
);

-- public.orders definition

CREATE TABLE public.orders (
                               id bigserial NOT NULL,
                               isdeleted bool NULL,
                               "number" int4 NULL,
                               localdatetime timestamp NULL,
                               consumer_id int8 NULL,
                               CONSTRAINT orders_pkey PRIMARY KEY (id)
);


-- public.orders foreign keys

ALTER TABLE public.orders ADD CONSTRAINT fkmblc2vl5o85vlag8aem93k4lc FOREIGN KEY (consumer_id) REFERENCES consumers(id);

-- public.order_items definition

CREATE TABLE public.order_items (
                                    id bigserial NOT NULL,
                                    amount int4 NULL,
                                    price int4 NULL,
                                    order_id int8 NULL,
                                    product_id int8 NULL,
                                    CONSTRAINT order_items_pkey PRIMARY KEY (id)
);


-- public.order_items foreign keys

ALTER TABLE public.order_items ADD CONSTRAINT fkbioxgbv59vetrxe0ejfubep1w FOREIGN KEY (order_id) REFERENCES orders(id);
ALTER TABLE public.order_items ADD CONSTRAINT fkocimc7dtr037rh4ls4l95nlfi FOREIGN KEY (product_id) REFERENCES products(id);

-- insert rows

INSERT INTO public.consumers
(id, isdeleted, "name")
VALUES(1, false, 'Василий Петрович');
INSERT INTO public.consumers
(id, isdeleted, "name")
VALUES(2, false, 'Семен Петрович');

INSERT INTO public.products
(id, isdeleted, price, title)
VALUES(3, false, 66, 'Водка');
INSERT INTO public.products
(id, isdeleted, price, title)
VALUES(1, false, 33, 'Майонез');
INSERT INTO public.products
(id, isdeleted, price, title)
VALUES(2, false, 55, 'Кетчуп');

INSERT INTO public.orders
(id, isdeleted, "number", localdatetime, consumer_id)
VALUES(1, false, 999, NULL, 1);

INSERT INTO public.order_items
(id, amount, price, order_id, product_id)
VALUES(2, 2, 45, 1, 1);
INSERT INTO public.order_items
(id, amount, price, order_id, product_id)
VALUES(3, 3, 77, 1, 3);