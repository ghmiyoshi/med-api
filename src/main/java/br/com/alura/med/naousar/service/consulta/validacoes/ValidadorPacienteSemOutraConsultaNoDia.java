package br.com.alura.med.naousar.service.consulta.validacoes;

import br.com.alura.med.infra.controllers.requests.DadosAgendamentoConsulta;
import br.com.alura.med.infra.handler.ValidacaoException;
import br.com.alura.med.infra.persistence.consulta.ConsultaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    private ConsultaRepository consultaRepository;

    @Override
    public void validar(final DadosAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia =
                consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(),
                        primeiroHorario,
                        ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }

}
