create extension if not exists "uuid-ossp";

create table if not exists client
(
    id            uuid primary key default uuid_generate_v4(),
    user_type     text,
    login         varchar(42),
    email         text,
    phone_number  text,
    creation_date date,
    update_date   date,
    latitude      float8,
    longitude     float8
);