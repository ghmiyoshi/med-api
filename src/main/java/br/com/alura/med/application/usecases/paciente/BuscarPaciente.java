package br.com.alura.med.application.usecases.paciente;

import br.com.alura.med.application.gateways.RepositorioPaciente;
import br.com.alura.med.domain.entities.paciente.Paciente;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarPaciente {

    private final RepositorioPaciente repositorio;

    public Paciente execute(Long id) {
        return repositorio.buscarPaciente(id);
    }
}
