package br.com.alura.med.infra.gateways;

import br.com.alura.med.application.gateways.RepositorioMedico;
import br.com.alura.med.domain.EnderecoValueObject;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.infra.controllers.mappers.EnderecoMapper;
import br.com.alura.med.infra.controllers.mappers.MedicoMapper;
import br.com.alura.med.infra.persistence.medico.MedicoEntity;
import br.com.alura.med.infra.persistence.medico.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
public class RepositorioMedicoJpa implements RepositorioMedico {

    private final MedicoRepository repository;
    private final MedicoMapper medicoMapper;
    private final EnderecoMapper enderecoMapper;

    @Override
    public Medico cadastrarMedico(final Medico medico) {
        if (repository.existsByCrm(medico.getCrm())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Médico já cadastrado");
        }
        var medicoEntity = medicoMapper.toEntity(medico);
        repository.save(medicoEntity);
        return medicoMapper.toDomain(medicoEntity);
    }

    @Override
    public Page<Medico> buscarTodos(final Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(medicoMapper::toDomain);
    }

    @Override
    public Medico atualizarMedico(final Long id, final Medico medico) {
        EnderecoValueObject endereco = medico.getEndereco();
        var medicoEntity = buscarMedicoEntity(id);
        medicoEntity.atualizarInformacoes(medico.getNome(), medico.getTelefone(),
                enderecoMapper.toEntity(endereco));
        medicoEntity = repository.save(medicoMapper.toEntity(medico));
        return medicoMapper.toDomain(medicoEntity);
    }

    @Override
    public void excluirMedico(final Long id) {
        repository.deleteById(id);
    }

    @Override
    public Medico buscarMedico(final Long id) {
        var medicoEntity = buscarMedicoEntity(id);
        return medicoMapper.toDomain(medicoEntity);
    }

    private MedicoEntity buscarMedicoEntity(final Long id) {
        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Medico não encontrado"));
    }
}
