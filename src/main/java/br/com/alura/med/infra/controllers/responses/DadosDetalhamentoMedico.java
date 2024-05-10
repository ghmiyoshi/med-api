package br.com.alura.med.infra.controllers.responses;

import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.infra.controllers.requests.DadosEndereco;

public record DadosDetalhamentoMedico(String nome,
                                      String email,
                                      String crm,
                                      Especialidade especialidade,
                                      DadosEndereco dadosEndereco) {
}
