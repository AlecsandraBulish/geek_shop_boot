CREATE TABLE product (
id serial NOT NULL primary key,
title text not null,
coast int not null,
image text
);

CREATE TABLE category (
id serial NOT NULL primary key ,
name varchar(15),
alias text not null
);

CREATE TABLE product_category (
product_id serial NOT NULL,
category_id serial NOT NULL,
PRIMARY KEY (product_id, category_id),
FOREIGN KEY (product_id) REFERENCES product(id),
FOREIGN KEY (category_id) REFERENCES category(id));





