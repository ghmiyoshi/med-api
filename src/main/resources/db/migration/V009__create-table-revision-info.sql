create table revision_info(
    id serial,
    timestamp bigint not null,
    created_by varchar(255) null,

    primary key(id)
);
