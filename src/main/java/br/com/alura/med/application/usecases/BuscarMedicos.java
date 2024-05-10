package br.com.alura.med.application.usecases;

import br.com.alura.med.application.gateways.RepositorioDeMedico;
import br.com.alura.med.domain.entities.medico.Medico;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarMedicos {

    private final RepositorioDeMedico repositorio;

    public Medico execute(Long id) {
        return repositorio.buscarMedico(id);
    }
}
