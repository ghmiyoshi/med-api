package br.com.alura.med.application.usecases.medico;

import br.com.alura.med.application.gateways.RepositorioMedico;
import br.com.alura.med.domain.entities.medico.Medico;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ListarMedicos {

    private final RepositorioMedico repositorio;

    public Page<Medico> execute(Pageable pageable) {
        return repositorio.buscarTodos(pageable);
    }
}
