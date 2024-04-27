package br.com.alura.med.naousar.service.consulta.validacoes;

import br.com.alura.med.naousar.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.med.naousar.infra.handler.ValidacaoException;
import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAntecedencia implements ValidadorAgendamentoDeConsulta {

    @Override
    public void validar(final DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();
        var dataHoraAtual = LocalDateTime.now();

        var diferencaEmMinutos = Duration.between(dataHoraAtual, dataConsulta).toMinutes();
        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de " +
                    "30 minutos");
        }
    }

}
