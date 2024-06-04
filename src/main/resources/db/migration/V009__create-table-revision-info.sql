create table revision_info(

    id bigint not null auto_increment,
    timestamp bigint not null,
    user varchar(255) null,

    primary key(id)
);
