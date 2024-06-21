package br.com.alura.med.application.usecases.medico;

import br.com.alura.med.application.gateways.RepositorioMedico;
import br.com.alura.med.domain.entities.medico.Medico;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarMedico {

    private final RepositorioMedico repositorio;

    public Medico execute(Long id, Medico medico) {
        return repositorio.atualizar(id, medico);
    }
}
