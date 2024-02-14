--liquibase formatted sql

--changeset muslimov_vlad:1
create table if not exists users (
                       id                    bigint not null,
                       name                  varchar(30) not null unique,
                       password              varchar(80) not null,
                       primary key (id)
);

--changeset muslimov_vlad:2
create table if not exists roles (
                       id                    bigint not null,
                       name                  varchar(50) not null,
                       primary key (id)
);

--changeset muslimov_vlad:3
CREATE TABLE if not exists users_roles (
                             user_id               bigint not null,
                             role_id               int not null,
                             primary key (user_id, role_id),
                             foreign key (user_id) references users (id),
                             foreign key (role_id) references roles (id)
);

--changeset muslimov_vlad:4
insert into roles (name)
values
    ('ROLE_USER'),
    ('ROLE_ADMIN');

--changeset muslimov_vlad:5
insert into users (username, password)
values
    ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
    ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

--changeset muslimov_vlad:6
insert into users_roles (user_id, role_id)
values
    (1, 1),
    (2, 2);