package br.com.alura.med.naousar.service.consulta.validacoes;

import br.com.alura.med.naousar.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.med.naousar.domain.repository.PacienteRepository;
import br.com.alura.med.naousar.infra.handler.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    private PacienteRepository pacienteRepository;

    @Override
    public void validar(final DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = pacienteRepository.existsByIdAndAtivo(dados.idPaciente(), true);
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }

}
