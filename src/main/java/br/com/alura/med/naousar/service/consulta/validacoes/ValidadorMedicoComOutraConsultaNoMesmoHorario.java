package br.com.alura.med.naousar.service.consulta.validacoes;

import br.com.alura.med.infra.controllers.requests.DadosAgendamentoConsulta;
import br.com.alura.med.infra.handler.ValidacaoException;
import br.com.alura.med.infra.persistence.consulta.ConsultaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    private ConsultaRepository consultaRepository;

    @Override
    public void validar(final DadosAgendamentoConsulta dados) {
        var medicoPossuiOutraConsultaNoMesmoHorario =
                consultaRepository.existsByMedicoIdAndData(dados.idMedico(),
                        dados.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo " +
                    "horário");
        }
    }

}
