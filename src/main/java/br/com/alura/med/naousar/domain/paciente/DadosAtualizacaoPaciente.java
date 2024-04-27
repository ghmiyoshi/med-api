package br.com.alura.med.naousar.domain.paciente;

import br.com.alura.med.naousar.domain.endereco.DadosEndereco;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(@NotNull Long id,
                                       String nome,
                                       String telefone,
                                       DadosEndereco endereco) {
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}