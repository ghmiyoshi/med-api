package br.com.alura.med.infra.controllers.responses;

import br.com.alura.med.infra.controllers.requests.DadosEndereco;

public record DadosListagemMedico(String nome,
                                  String email,
                                  String crm,
                                  String especialidade,
                                  DadosEndereco endereco) {
}
