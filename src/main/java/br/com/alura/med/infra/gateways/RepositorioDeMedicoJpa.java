package br.com.alura.med.infra.gateways;

import br.com.alura.med.application.gateways.RepositorioDeMedico;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.infra.persistence.MedicoEntity;
import br.com.alura.med.infra.persistence.MedicoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RepositorioDeMedicoJpa implements RepositorioDeMedico {

    private final MedicoRepository repository;
    private final MedicoEntityMapper mapper;

    @Override
    public Medico cadastrarMedico(Medico medico) {
        MedicoEntity medicoEntity = mapper.toEntity(medico);
        repository.save(medicoEntity);
        return mapper.toDomain(medicoEntity);
    }

    @Override
    public List<Medico> buscarTodos() {
        return List.of();
    }

    @Override
    public Medico atualizarMedico(Medico medico) {
        return null;
    }

    @Override
    public void excluirMedico(Long id) {

    }
}
