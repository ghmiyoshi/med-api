package br.com.alura.med.infra.gateways.validacoes;

import static java.util.Objects.isNull;

import br.com.alura.med.application.gateways.ValidadorAgendamentoDeConsulta;
import br.com.alura.med.infra.controllers.requests.DadosAgendamentoConsulta;
import br.com.alura.med.infra.handler.ValidacaoException;
import br.com.alura.med.infra.persistence.medico.MedicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    private MedicoRepository medicoRepository;

    @Override
    public void validar(final DadosAgendamentoConsulta dados) {
        if (isNull(dados.idMedico())) {
            return;
        }

        var medicoEstaAtivo = medicoRepository.existsByIdAndAtivo(dados.idMedico(), true);
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo");
        }
    }

}
