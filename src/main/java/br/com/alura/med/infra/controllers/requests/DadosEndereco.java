package br.com.alura.med.infra.controllers.requests;

import br.com.alura.med.infra.utils.ObjectMapperUtils;
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
    @Override
    public String toString() {
        return ObjectMapperUtils.writeObjectInJson(this);
    }
}
