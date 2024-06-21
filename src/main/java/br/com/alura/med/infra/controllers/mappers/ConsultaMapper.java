package br.com.alura.med.infra.controllers.mappers;

import br.com.alura.med.domain.entities.consulta.Consulta;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.domain.entities.paciente.Paciente;
import br.com.alura.med.infra.controllers.responses.DadosDetalhamentoConsulta;
import br.com.alura.med.infra.persistence.consulta.ConsultaEntity;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConsultaMapper {

    private final MedicoMapper medicoMapper;
    private final PacienteMapper pacienteMapper;

    public ConsultaEntity toEntity(final Medico medico, final Paciente paciente,
                                   final LocalDateTime data) {
        return new ConsultaEntity(medicoMapper.toEntity(medico),
                pacienteMapper.toEntity(paciente),
                data);
    }

    public Consulta toDomain(final ConsultaEntity consultaEntity) {
        return new Consulta(medicoMapper.toDomain(consultaEntity.getMedico()),
                pacienteMapper.toDomain(consultaEntity.getPaciente()),
                consultaEntity.getData());
    }

    public DadosDetalhamentoConsulta toResponse(final Consulta consulta) {
        return new DadosDetalhamentoConsulta(consulta.getMedico().getNome(),
                consulta.getPaciente().getNome(),
                consulta.getData());
    }
}
