package br.com.alura.med.infra.controllers.responses;

public record DadosListagemPaciente(String nome,
                                    String email,
                                    String cpf) {
}
