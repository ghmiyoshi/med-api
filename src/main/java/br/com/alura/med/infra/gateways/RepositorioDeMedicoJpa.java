package br.com.alura.med.infra.gateways;

import br.com.alura.med.application.gateways.RepositorioDeMedico;
import br.com.alura.med.domain.EnderecoValueObject;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.infra.controllers.mappers.EnderecoMapper;
import br.com.alura.med.infra.controllers.mappers.MedicoMapper;
import br.com.alura.med.infra.persistence.medico.MedicoEntity;
import br.com.alura.med.infra.persistence.medico.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class RepositorioDeMedicoJpa implements RepositorioDeMedico {

    private final MedicoRepository repository;
    private final MedicoMapper medicoMapper;
    private final EnderecoMapper enderecoMapper;

    @Override
    public Medico cadastrarMedico(Medico medico) {
        var medicoEntity = medicoMapper.toEntity(medico);
        repository.save(medicoEntity);
        return medicoMapper.toDomain(medicoEntity);
    }

    @Override
    public Page<Medico> buscarTodos(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(medicoMapper::toDomain);
    }

    @Override
    public Medico atualizarMedico(Long id, Medico medico) {
        EnderecoValueObject endereco = medico.getEndereco();
        var medicoEntity = buscarMedicoEntity(id);
        medicoEntity.atualizarInformacoes(medico.getNome(), medico.getTelefone(),
                enderecoMapper.toEntity(endereco));
        medicoEntity = repository.save(medicoMapper.toEntity(medico));
        return medicoMapper.toDomain(medicoEntity);
    }

    @Override
    public void excluirMedico(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Medico buscarMedico(Long id) {
        var medicoEntity = buscarMedicoEntity(id);
        return medicoMapper.toDomain(medicoEntity);
    }

    private MedicoEntity buscarMedicoEntity(final Long id) {
        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Medico não encontrado"));
    }
}
