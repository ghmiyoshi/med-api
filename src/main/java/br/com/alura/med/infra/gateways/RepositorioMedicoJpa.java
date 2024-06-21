package br.com.alura.med.infra.gateways;

import br.com.alura.med.application.gateways.RepositorioMedico;
import br.com.alura.med.domain.EnderecoValueObject;
import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.infra.controllers.mappers.EnderecoMapper;
import br.com.alura.med.infra.controllers.mappers.MedicoMapper;
import br.com.alura.med.infra.persistence.medico.MedicoEntity;
import br.com.alura.med.infra.persistence.medico.MedicoRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.function.Supplier;
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
    public Medico cadastrar(final Medico medico) {
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
    public Medico atualizar(final Long id, final Medico medico) {
        EnderecoValueObject endereco = medico.getEndereco();
        var medicoEntity = buscarMedicoEntity(id);
        medicoEntity.atualizarInformacoes(medico.getNome(), medico.getTelefone(),
                enderecoMapper.toEntity(endereco));
        medicoEntity = repository.save(medicoMapper.toEntity(medico));
        return medicoMapper.toDomain(medicoEntity);
    }

    @Override
    public void excluir(final Long id) {
        repository.deleteById(id);
    }

    @Override
    public Medico buscar(final Long id) {
        var medicoEntity = buscarMedicoEntity(id);
        return medicoMapper.toDomain(medicoEntity);
    }

    @Override
    public Medico buscarMedicoAleatorioLivreNaData(final Especialidade especialidade,
                                                   final LocalDateTime data) {
        return repository.escolherMedicoAleatorioLivreNaData(especialidade, data)
                .map(medicoMapper::toDomain)
                .orElseThrow(getMedicoNaoEncontrado());
    }

    private MedicoEntity buscarMedicoEntity(final Long id) {
        return repository.findById(id).orElseThrow(
                getMedicoNaoEncontrado());
    }

    private Supplier<EntityNotFoundException> getMedicoNaoEncontrado() {
        return () -> new EntityNotFoundException("Médico não encontrado");
    }
}
