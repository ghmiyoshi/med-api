package br.com.alura.med.application.usecases.medico;

import br.com.alura.med.application.gateways.RepositorioMedico;
import br.com.alura.med.domain.entities.medico.Medico;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarMedico {

    private final RepositorioMedico repositorio;

    public Medico execute(Long id) {
        return repositorio.buscarMedico(id);
    }
}
