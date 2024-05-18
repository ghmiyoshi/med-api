package br.com.alura.med.infra.persistence.paciente;

import static java.util.Objects.nonNull;

import br.com.alura.med.infra.persistence.medico.EnderecoEntity;
import br.com.alura.med.infra.utils.JsonAbstract;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
@Table(name = "tb_pacientes")
@Entity
public class PacienteEntity extends JsonAbstract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    @Embedded
    private EnderecoEntity endereco;

    private boolean ativo;

    public PacienteEntity(String nome, String email, String telefone, String cpf,
                          EnderecoEntity endereco) {
        this.ativo = true;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public PacienteEntity atualizarInformacoes(String nome, String telefone,
                                               EnderecoEntity endereco) {
        if (nonNull(nome)) {
            this.nome = nome;
        }
        if (nonNull(telefone)) {
            this.telefone = telefone;
        }
        if (nonNull(endereco)) {
            this.endereco.atualizarInformacoes(endereco);
        }
        return this;
    }

    public void excluir() {
        this.ativo = false;
    }
}
