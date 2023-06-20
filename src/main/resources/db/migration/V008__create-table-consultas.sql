create table tb_consultas(

    id bigint not null auto_increment,
    medico_id bigint not null,
    paciente_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_consultas_medico_id foreign key(medico_id) references tb_medicos(id),
    constraint fk_consultas_paciente_id foreign key(paciente_id) references tb_pacientes(id)
);