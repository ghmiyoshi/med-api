package br.com.alura.med.application.usecases.consulta;

import br.com.alura.med.application.gateways.RepositorioConsulta;
import br.com.alura.med.domain.entities.consulta.Consulta;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarConsulta {

    private final RepositorioConsulta repositorio;

    public Consulta execute(final Long id) {
        return repositorio.buscar(id);
    }
}
