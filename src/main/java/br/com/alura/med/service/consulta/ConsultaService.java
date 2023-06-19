package br.com.alura.med.service.consulta;

import br.com.alura.med.domain.consulta.Consulta;
import br.com.alura.med.domain.consulta.DadosAgendamentoConsulta;
import br.com.alura.med.domain.consulta.DadosDetalhamentoConsulta;
import br.com.alura.med.domain.medico.Medico;
import br.com.alura.med.domain.repository.ConsultaRepository;
import br.com.alura.med.domain.repository.MedicoRepository;
import br.com.alura.med.domain.repository.PacienteRepository;
import br.com.alura.med.infra.handler.ValidacaoException;
import br.com.alura.med.service.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<ValidadorAgendamentoDeConsulta> validacoesAgendamentoDeConsulta;

    public DadosDetalhamentoConsulta agendar(final DadosAgendamentoConsulta dados) {
        log.info("{}::agendar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var medico = escolherMedico(dados);

        var paciente = pacienteRepository.findById(dados.idPaciente()).orElseThrow(
                () -> new EntityNotFoundException("Paciente não encontrado"));

        validacoesAgendamentoDeConsulta.forEach(validacao -> validacao.validar(dados));

        var consulta = new Consulta(null, medico, paciente, dados.data());
        consulta = consultaRepository.save(consulta);

        log.info("{}::agendar - Dados salvos: {}", getClass().getSimpleName(), consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(final DadosAgendamentoConsulta dados) {
        if (nonNull(dados.idMedico())) {
            log.info("{}::escolherMedico - Busca médico: {}", getClass().getSimpleName(), dados.idMedico());
            return medicoRepository.findById(dados.idMedico()).orElseThrow(
                    () -> new EntityNotFoundException("Medico não encontrado"));
        }

        if (isNull(dados.especialidade())) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido");
        }

        log.info("{}::escolherMedico - Busca médico aleatório: {}", getClass().getSimpleName(), dados.especialidade());
        var medico = medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

        if (isNull(medico)) {
            throw new ValidacaoException("Não existe médico disponível nessa data");
        }

        log.info("{}::escolherMedico - Médico selecionado: {}", getClass().getSimpleName(), medico.getId());
        return medico;
    }

}
