package br.com.alura.med.infra.gateways;

import br.com.alura.med.application.gateways.RepositorioDeMedico;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.infra.persistence.MedicoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RepositorioDeMedicoJpa implements RepositorioDeMedico {

    private final MedicoRepository repository;

    @Override
    public Medico cadastrarMedico(Medico medico) {
        return repository.save(medico);
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
