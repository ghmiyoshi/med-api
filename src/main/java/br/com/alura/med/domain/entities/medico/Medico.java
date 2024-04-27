package br.com.alura.med.domain.entities.medico;

import br.com.alura.med.domain.entities.EnderecoValueObject;
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
    private EnderecoValueObject endereco;
    private boolean ativo;

    public Medico(String nome, String email, String telefone, String crm,
                  String especialidade,
                  EnderecoValueObject endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.especialidade = especialidade;
        this.endereco = endereco;
        this.ativo = true;
    }

    public Medico(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }
}
