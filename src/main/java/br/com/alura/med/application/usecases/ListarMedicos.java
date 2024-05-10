package br.com.alura.med.application.usecases;

import br.com.alura.med.application.gateways.RepositorioDeMedico;
import br.com.alura.med.domain.entities.medico.Medico;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ListarMedicos {

    private final RepositorioDeMedico repositorio;

    public Page<Medico> execute(Pageable pageable) {
        return repositorio.buscarTodos(pageable);
    }
}
