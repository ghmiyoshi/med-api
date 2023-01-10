package br.com.alura.med.domain.model;

import br.com.alura.med.domain.record.DadosAtualizacaoMedico;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.Objects.nonNull;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tb_medicos")
@Entity
public class Medico {

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
    private Boolean ativo;

    public Medico(String nome, String telefone, Endereco endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.ativo = true;
    }

    public Medico(String nome, String email, String telefone, String crm, Especialidade especialidade, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.crm = crm;
        this.especialidade = especialidade;
        this.endereco = endereco;
        this.ativo = true;
    }

    public Medico atualizarInformacoes(DadosAtualizacaoMedico dados) {
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
        if (this.ativo.equals(true)) {
            this.ativo = false;
        } else {
            throw new RuntimeException("Medico já está inativo");
        }
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
