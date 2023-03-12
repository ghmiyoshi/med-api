package br.com.alura.med.service.consulta.validacoes;

import br.com.alura.med.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.med.infra.handler.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;


@Component
public class ValidadorHorarioClinica implements ValidadorAgendamentoDeConsulta {

    @Override
    public void validar(final DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var eDomingo = DayOfWeek.SUNDAY.equals(dataConsulta.getDayOfWeek());
        var foraDoHorarioDeFuncionamentoDaClinica = dataConsulta.getHour() < 7 || dataConsulta.getHour() > 18;

        if (eDomingo || foraDoHorarioDeFuncionamentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }

}
