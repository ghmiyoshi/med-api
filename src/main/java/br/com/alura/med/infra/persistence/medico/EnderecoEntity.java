package br.com.alura.med.infra.persistence.medico;

import static java.util.Objects.nonNull;

import br.com.alura.med.infra.controllers.requests.DadosEndereco;
import br.com.alura.med.infra.utils.JsonAbstract;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class EnderecoEntity extends JsonAbstract {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public EnderecoEntity(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    public void atualizarInformacoes(EnderecoEntity enderecoEntity) {
        if (nonNull(enderecoEntity.getLogradouro())) {
            this.logradouro = enderecoEntity.getLogradouro();
        }
        if (nonNull(enderecoEntity.getBairro())) {
            this.bairro = enderecoEntity.getBairro();
        }
        if (nonNull(enderecoEntity.getCep())) {
            this.cep = enderecoEntity.getCep();
        }
        if (nonNull(enderecoEntity.getCidade())) {
            this.cidade = enderecoEntity.getCidade();
        }
        if (nonNull(enderecoEntity.getUf())) {
            this.uf = enderecoEntity.getUf();
        }
        if (nonNull(enderecoEntity.getComplemento())) {
            this.complemento = enderecoEntity.getComplemento();
        }
        if (nonNull(enderecoEntity.getNumero())) {
            this.numero = enderecoEntity.getNumero();
        }
    }
}
