create table tb_usuarios_perfis(

    usuario_id bigint not null,
    perfil_id bigint not null,

    foreign key (usuario_id) references tb_usuarios(id),
    foreign key (perfil_id) references tb_perfis(id)

);