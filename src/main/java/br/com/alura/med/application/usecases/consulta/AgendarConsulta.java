package br.com.alura.med.application.usecases.consulta;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import br.com.alura.med.application.gateways.ValidadorAgendamentoDeConsulta;
import br.com.alura.med.application.usecases.medico.BuscarMedico;
import br.com.alura.med.application.usecases.paciente.BuscarPaciente;
import br.com.alura.med.domain.entities.consulta.Consulta;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.infra.controllers.requests.DadosAgendamentoConsulta;
import br.com.alura.med.infra.handler.ValidacaoException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgendarConsulta {

    private static final String CONSULTA_AGENDADA = "Consulta agendada com sucesso";
    private final CriarConsulta criarConsulta;
    private final BuscarMedico buscarMedico;
    private final BuscarPaciente buscarPaciente;
    private final List<ValidadorAgendamentoDeConsulta> validacoesAgendamentoDeConsulta;

    public Consulta execute(final DadosAgendamentoConsulta dados) {
        log.info("{}::agendar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var medico = escolherMedico(dados);
        log.info("{}::agendar - Médico selecionado: {}", getClass().getSimpleName(),
                medico.getNome());

        var paciente = buscarPaciente.execute(dados.idPaciente());

        validacoesAgendamentoDeConsulta.forEach(validacao -> validacao.validar(dados));

        var consulta = criarConsulta.execute(medico, paciente, dados.data());

        return consulta;
    }

    private Medico escolherMedico(final DadosAgendamentoConsulta dados) {
        if (nonNull(dados.idMedico())) {
            log.info("{}::escolherMedico - Busca médico: {}", getClass().getSimpleName(),
                    dados.idMedico());
            return buscarMedico.execute(dados.idMedico());
        }

        if (isNull(dados.especialidade())) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for " +
                    "escolhido");
        }

        log.info("{}::escolherMedico - Busca médico aleatório: {}", getClass().getSimpleName(),
                dados.especialidade());
        return buscarMedico.execute(dados.especialidade(),
                dados.data());
    }
}
