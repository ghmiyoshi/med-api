create table tb_usuarios(

    id serial,
    login varchar(100) not null,
    senha varchar(255) not null,

    primary key(id)

);