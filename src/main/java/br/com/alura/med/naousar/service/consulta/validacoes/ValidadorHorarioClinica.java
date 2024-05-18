package br.com.alura.med.naousar.service.consulta.validacoes;

import br.com.alura.med.infra.controllers.requests.DadosAgendamentoConsulta;
import br.com.alura.med.infra.handler.ValidacaoException;
import java.time.DayOfWeek;
import org.springframework.stereotype.Component;


@Component
public class ValidadorHorarioClinica implements ValidadorAgendamentoDeConsulta {

    @Override
    public void validar(final DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var eDomingo = DayOfWeek.SUNDAY.equals(dataConsulta.getDayOfWeek());
        var foraDoHorarioDeFuncionamentoDaClinica =
                dataConsulta.getHour() < 7 || dataConsulta.getHour() > 18;

        if (eDomingo || foraDoHorarioDeFuncionamentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }

}
