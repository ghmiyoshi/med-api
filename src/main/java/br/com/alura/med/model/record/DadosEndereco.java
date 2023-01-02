package br.com.alura.med.model.record;

import br.com.alura.med.model.Endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        String complemento,
        String numero) {

    public Endereco converterParaEndereco() {
        return new Endereco(logradouro, bairro, cep, cidade, uf, complemento, numero);
    }

}
