package br.com.alura.med.model;

import br.com.alura.med.model.record.DadosEndereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import static java.util.Objects.nonNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public void atualizarInformacoes(DadosEndereco dados) {
        if (nonNull(dados.logradouro())) {
            this.logradouro = dados.logradouro();
        }
        if (nonNull(dados.bairro())) {
            this.bairro = dados.bairro();
        }
        if (nonNull(dados.cep())) {
            this.cep = dados.cep();
        }
        if (nonNull(dados.cidade())) {
            this.cidade = dados.cidade();
        }
        if (nonNull(dados.uf())) {
            this.uf = dados.uf();
        }
        if (nonNull(dados.complemento())) {
            this.complemento = dados.complemento();
        }
        if (nonNull(dados.numero())) {
            this.numero = dados.numero();
        }
    }
}
