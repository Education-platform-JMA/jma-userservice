create table users
(
    id       bigserial    not null,
    password varchar(255) not null,
    username varchar(255) not null,
    primary key (id)
)
GO