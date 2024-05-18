package br.com.alura.med.infra.gateways;

import br.com.alura.med.application.gateways.RepositorioPaciente;
import br.com.alura.med.domain.EnderecoValueObject;
import br.com.alura.med.domain.entities.paciente.Paciente;
import br.com.alura.med.infra.controllers.mappers.EnderecoMapper;
import br.com.alura.med.infra.controllers.mappers.PacienteMapper;
import br.com.alura.med.infra.persistence.paciente.PacienteEntity;
import br.com.alura.med.infra.persistence.paciente.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class RepositorioPacienteJpa implements RepositorioPaciente {

    private final PacienteRepository repository;
    private final PacienteMapper pacienteMapper;
    private final EnderecoMapper enderecoMapper;

    @Override
    public Paciente cadastrarPaciente(final Paciente paciente) {
        var medicoEntity = pacienteMapper.toEntity(paciente);
        repository.save(medicoEntity);
        return pacienteMapper.toDomain(medicoEntity);
    }

    @Override
    public Page<Paciente> buscarTodos(final Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(pacienteMapper::toDomain);
    }

    @Override
    public Paciente atualizarPaciente(final Long id, final Paciente paciente) {
        EnderecoValueObject endereco = paciente.getEndereco();
        var pacienteEntity = buscarPacienteEntity(id);
        pacienteEntity.atualizarInformacoes(paciente.getNome(), paciente.getTelefone(),
                enderecoMapper.toEntity(endereco));
        pacienteEntity = repository.save(pacienteMapper.toEntity(paciente));
        return pacienteMapper.toDomain(pacienteEntity);
    }

    @Override
    public void excluirPaciente(final Long id) {
        repository.deleteById(id);
    }

    @Override
    public Paciente buscarPaciente(final Long id) {
        var pacienteEntity = buscarPacienteEntity(id);
        return pacienteMapper.toDomain(pacienteEntity);
    }

    private PacienteEntity buscarPacienteEntity(final Long id) {
        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Paciente não encontrado"));
    }
}
