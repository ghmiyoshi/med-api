package br.com.alura.med.application.usecases.paciente;

import br.com.alura.med.application.gateways.RepositorioPaciente;
import br.com.alura.med.domain.entities.paciente.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class ListarPacientes {

    private final RepositorioPaciente repositorio;

    public Page<Paciente> execute(Pageable pageable) {
        return repositorio.buscarTodos(pageable);
    }
}
