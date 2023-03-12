package br.com.alura.med.service.consulta.validacoes;

import br.com.alura.med.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.med.domain.repository.PacienteRepository;
import br.com.alura.med.infra.handler.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    private final PacienteRepository pacienteRepository;

    @Override
    public void validar(final DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = pacienteRepository.existsByIdAndAtivo(dados.idPaciente(), true);
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }

}