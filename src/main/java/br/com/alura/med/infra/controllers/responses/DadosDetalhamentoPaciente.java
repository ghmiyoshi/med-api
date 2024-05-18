package br.com.alura.med.infra.controllers.responses;

import br.com.alura.med.infra.controllers.requests.DadosEndereco;

public record DadosDetalhamentoPaciente(String nome,
                                        String email,
                                        String cpf,
                                        String telefone,
                                        DadosEndereco endereco) {
}
