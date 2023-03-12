package br.com.alura.med.domain.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(@NotBlank String logradouro,
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
