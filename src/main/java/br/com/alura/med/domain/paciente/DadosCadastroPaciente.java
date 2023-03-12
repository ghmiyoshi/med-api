package br.com.alura.med.domain.paciente;

import br.com.alura.med.domain.endereco.DadosEndereco;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPaciente(@NotBlank String nome,

                                    @NotBlank
                                    @Email
                                    String email,

                                    @NotBlank
                                    String telefone,

                                    @NotBlank
                                    @Pattern(regexp = "\\d{3}\\.\\d{3}\\" +
                                            ".\\d{3}\\-\\d{2}")
                                    String cpf,

                                    @NotNull @Valid DadosEndereco endereco) {

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}