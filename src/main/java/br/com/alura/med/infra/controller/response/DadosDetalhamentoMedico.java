package br.com.alura.med.infra.controller.response;

import br.com.alura.med.domain.EnderecoValueObject;
import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.domain.entities.medico.Medico;

public record DadosDetalhamentoMedico(String nome,
                                      String email,
                                      String crm,
                                      Especialidade especialidade,
                                      EnderecoValueObject dadosEndereco) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(),
                medico.getEndereco());
    }

}
