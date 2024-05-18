package br.com.alura.med.infra.persistence.medico;

import static java.util.Objects.nonNull;

import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.infra.persistence.Auditable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
@NoArgsConstructor
@Audited
@EqualsAndHashCode(of = "id")
@Table(name = "tb_medicos")
@Entity
public class MedicoEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private EnderecoEntity endereco;
    private boolean ativo;

    public MedicoEntity(String nome, String email, String telefone, String crm,
                        Especialidade especialidade, EnderecoEntity endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.especialidade = especialidade;
        this.endereco = endereco;
        this.ativo = true;
    }

    public MedicoEntity atualizarInformacoes(String nome, String telefone,
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

    public void desativar() {
        if (this.ativo) {
            this.ativo = false;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Medico já está inativo");
        }
    }
}
