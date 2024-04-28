package br.com.alura.med.infra.persistence;

import static java.util.Objects.nonNull;

import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.infra.controller.request.DadosCadastroMedico;
import br.com.alura.med.naousar.domain.audit.Auditable;
import br.com.alura.med.naousar.domain.endereco.Endereco;
import br.com.alura.med.naousar.domain.medico.DadosAtualizacaoMedico;
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
    private Endereco endereco;
    private boolean ativo;

    public MedicoEntity(String nome, String email, String telefone, String crm,
                        Especialidade especialidade,
                        Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.especialidade = especialidade;
        this.endereco = endereco;
        this.ativo = true;
    }

    public MedicoEntity(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public MedicoEntity(Medico medico) {
        this.nome = medico.getNome();
        this.email = medico.getEmail();
        this.telefone = medico.getTelefone();
        this.crm = medico.getCrm();
        this.especialidade = medico.getEspecialidade();
        this.ativo = true;
    }

    public MedicoEntity atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (nonNull(dados.nome())) {
            this.nome = dados.nome();
        }
        if (nonNull(dados.telefone())) {
            this.telefone = dados.telefone();
        }
        if (nonNull(dados.endereco())) {
            this.endereco.atualizarInformacoes(dados.endereco());
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
