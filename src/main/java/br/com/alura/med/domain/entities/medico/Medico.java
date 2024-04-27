package br.com.alura.med.domain.entities.medico;

import br.com.alura.med.domain.entities.EnderecoValueObject;
import br.com.alura.med.infra.persistence.MedicoEntity;
import br.com.alura.med.naousar.domain.medico.Especialidade;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medico {

    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Especialidade especialidade;
    private EnderecoValueObject endereco;
    private boolean ativo;

    public Medico(String nome, String email, String telefone, String crm,
                  Especialidade especialidade,
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

    public Medico(MedicoEntity medicoEntity) {
        this.nome = medicoEntity.getNome();
        this.email = medicoEntity.getEmail();
        this.telefone = medicoEntity.getTelefone();
        this.crm = medicoEntity.getCrm();
        this.especialidade = medicoEntity.getEspecialidade();
        this.ativo = true;
    }
}
