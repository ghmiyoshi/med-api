create table tb_medicos(

    id serial,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    especialidade varchar(100) not null,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    created_by varchar(100) null,
    last_modified_by varchar(100) null,
    creation_date      timestamp not null,
    last_modified_date timestamp not null,

    primary key(id)
);
