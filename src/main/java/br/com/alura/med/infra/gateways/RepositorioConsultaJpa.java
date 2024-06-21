package br.com.alura.med.infra.gateways;

import br.com.alura.med.application.gateways.RepositorioConsulta;
import br.com.alura.med.domain.entities.consulta.Consulta;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.domain.entities.paciente.Paciente;
import br.com.alura.med.infra.controllers.mappers.ConsultaMapper;
import br.com.alura.med.infra.event.ConsultaEvent;
import br.com.alura.med.infra.persistence.consulta.ConsultaRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class RepositorioConsultaJpa implements RepositorioConsulta {

    private final ConsultaRepository repository;
    private final ConsultaMapper consultaMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public Consulta cadastrar(Medico medico, Paciente paciente, LocalDateTime data) {
        var consultaEntity = consultaMapper.toEntity(medico, paciente, data);
        repository.save(consultaEntity);
        applicationEventPublisher.publishEvent(new ConsultaEvent(consultaEntity));
        return consultaMapper.toDomain(consultaEntity);
    }

    @Override
    public Consulta buscar(Long id) {
        return repository.findById(id)
                .map(consultaMapper::toDomain)
                .orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));
    }
}
