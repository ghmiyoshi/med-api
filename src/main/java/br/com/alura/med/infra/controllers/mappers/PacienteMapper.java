package br.com.alura.med.infra.controllers.mappers;

import br.com.alura.med.domain.entities.paciente.Paciente;
import br.com.alura.med.infra.controllers.responses.DadosDetalhamentoPaciente;
import br.com.alura.med.infra.controllers.responses.DadosListagemPaciente;
import br.com.alura.med.infra.persistence.paciente.PacienteEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PacienteMapper {

    private final EnderecoMapper enderecoMapper;

    public DadosListagemPaciente toResponseListagem(final Paciente paciente) {
        return new DadosListagemPaciente(paciente.getNome(), paciente.getEmail(),
                paciente.getCpf());
    }

    public DadosDetalhamentoPaciente toResponseDetalhamento(final Paciente paciente) {
        final var dadosEndereco = enderecoMapper.toResponse(paciente.getEndereco());
        return new DadosDetalhamentoPaciente(paciente.getNome(), paciente.getEmail(),
                paciente.getCpf(), paciente.getTelefone(), dadosEndereco);
    }

    public PacienteEntity toEntity(final Paciente paciente) {
        return new PacienteEntity(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(),
                paciente.getCpf(), enderecoMapper.toEntity(paciente.getEndereco()));
    }

    public Paciente toDomain(final PacienteEntity pacienteEntity) {
        return new Paciente(pacienteEntity.getNome(),
                pacienteEntity.getEmail(), pacienteEntity.getTelefone(),
                pacienteEntity.getCpf(), enderecoMapper.toDomain(pacienteEntity.getEndereco()),
                true);
    }
}
