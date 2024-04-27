package br.com.alura.med.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medico {

    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private String especialidade;
    private boolean ativo;
}
