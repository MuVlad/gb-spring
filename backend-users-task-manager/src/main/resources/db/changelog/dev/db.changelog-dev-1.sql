--liquibase formatted sql

--changeset muslimov_vlad:1
create sequence if not exists user_seq start with 3 increment by 1;

--changeset muslimov_vlad:2
create table if not exists users (
                       id                    bigint not null,
                       name                  varchar(30) not null unique,
                       password              varchar(80) not null,
                       role                  varchar(30),
                       primary key (id)
);

--changeset muslimov_vlad:3
insert into users (id,name, password, role)
values
    (1, 'user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'USER'),
    (2, 'admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'ADMIN');