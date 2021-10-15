CREATE TABLE type
(
    id   serial primary key,
    name varchar(2000)
);

CREATE TABLE rule
(
    id   serial primary key,
    name varchar(2000)
);

CREATE TABLE accident
(
    id      serial primary key,
    name    varchar(2000),
    text    text,
    address text,
    type_id int references type(id)
);

CREATE TABLE accident_rule
(
    id serial primary key,
    accident_id int references accident(id),
    rule_id int references rule(id)
);
insert into type(name) values('Две машины');
insert into type(name) values('Машина и человек');
insert into type(name) values('Машина и велосипед');
insert into rule(name) values('Статья 1');
insert into rule(name) values('Статья 2');
insert into rule(name) values('Статья 3');