package br.com.alura.med.naousar.service.consulta;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import br.com.alura.med.infra.controllers.requests.DadosAgendamentoConsulta;
import br.com.alura.med.infra.controllers.responses.DadosDetalhamentoConsulta;
import br.com.alura.med.infra.event.ConsultaEvent;
import br.com.alura.med.infra.handler.ValidacaoException;
import br.com.alura.med.infra.persistence.consulta.ConsultaEntity;
import br.com.alura.med.infra.persistence.consulta.ConsultaRepository;
import br.com.alura.med.infra.persistence.medico.MedicoEntity;
import br.com.alura.med.infra.persistence.medico.MedicoRepository;
import br.com.alura.med.infra.persistence.paciente.PacienteRepository;
import br.com.alura.med.naousar.service.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<ValidadorAgendamentoDeConsulta> validacoesAgendamentoDeConsulta;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ObjectMapper objectMapper;

    public DadosDetalhamentoConsulta agendar(final DadosAgendamentoConsulta dados) {
        log.info("{}::agendar - Dados recebidos: {}", getClass().getSimpleName(), dados);
        var medico = escolherMedico(dados);

        var paciente = pacienteRepository.findById(dados.idPaciente()).orElseThrow(
                () -> new EntityNotFoundException("Paciente não encontrado"));

        validacoesAgendamentoDeConsulta.forEach(validacao -> validacao.validar(dados));

        var consulta = new ConsultaEntity(null, medico, paciente, dados.data());
        consulta = consultaRepository.save(consulta);

        try {
            log.info("{}::agendar - Dados salvos: {}", getClass().getSimpleName(),
                    objectMapper.writeValueAsString(consulta));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        applicationEventPublisher.publishEvent(new ConsultaEvent(consulta));
//        return new DadosDetalhamentoConsulta(consulta);
        return null;
    }

    private MedicoEntity escolherMedico(final DadosAgendamentoConsulta dados) {
        if (nonNull(dados.idMedico())) {
            log.info("{}::escolherMedico - Busca médico: {}", getClass().getSimpleName(),
                    dados.idMedico());
            return medicoRepository.findById(dados.idMedico()).orElseThrow(
                    () -> new EntityNotFoundException("Medico não encontrado"));
        }

        if (isNull(dados.especialidade())) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for " +
                    "escolhido");
        }

        log.info("{}::escolherMedico - Busca médico aleatório: {}", getClass().getSimpleName(),
                dados.especialidade());
        var medico = medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),
                dados.data());

        if (isNull(medico)) {
            throw new ValidacaoException("Não existe médico disponível nessa data");
        }

        log.info("{}::escolherMedico - Médico selecionado: {}", getClass().getSimpleName(),
                medico.getId());
        return medico;
    }

    public DadosDetalhamentoConsulta buscarAgendamento(final Long id) {
        return null;
        //        return consultaRepository.findById(id).map(DadosDetalhamentoConsulta::new)
        //        .orElseThrow(
//                () -> new EntityNotFoundException("Consulta não encontrada"));
    }

}
