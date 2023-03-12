package br.com.alura.med.service.consulta.validacoes;

import br.com.alura.med.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.med.domain.repository.MedicoRepository;
import br.com.alura.med.infra.handler.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@AllArgsConstructor
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    private final MedicoRepository medicoRepository;

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
