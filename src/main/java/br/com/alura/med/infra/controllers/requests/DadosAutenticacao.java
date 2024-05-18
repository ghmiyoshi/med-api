package br.com.alura.med.infra.controllers.requests;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(@NotBlank(message = "Login é obrigatório") String login,
                                @NotBlank(message = "Senha é obrigatório")
                                String senha) {
}
