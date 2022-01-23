CREATE TABLE users (
    id serial NOT NULL primary key,
    username varchar(15),
    password varchar(100),
    email varchar (24),
    enabled boolean default true
    );

CREATE TABLE role (
    id serial NOT NULL primary key ,
    name varchar(25)
    );

CREATE TABLE user_role (
    user_id int NOT NULL,
    role_id int NOT NULL,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references role (id));

insert into role (name) values ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');


INSERT INTO users (username, password, email)
VALUES
    ('alex', '$2a$12$.htJwHfX3/JV3H.OOLfAJOvf4rIlroFjafrR3DlR.zWk3UhxN/hVy', 'alex@mail.com'),
    ('elena', '$2a$12$OLm5rHbUqHt.nmzpi3JvWuqtD0DOxvc5vbJXCGMLzKZu9yIYqfw26', 'elena@mail.com'),
    ('ivan', '$2a$12$Lz2lKNLlFJWEecu1JDR9L.qEKB2lrcCBSDzu.YjjNHJC3xg6kjpwC', 'ivan@mail.com');

insert into user_role (user_id, role_id)
values (1, 1),
       (2, 3),
       (3, 2);



