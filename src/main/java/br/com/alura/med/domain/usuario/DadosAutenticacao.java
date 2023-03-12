package br.com.alura.med.domain.usuario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(@NotBlank(message = "Login é obrigatório") String login,
                                @NotBlank(message = "Senha é obrigatório")
                                String senha) {
    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    
}
