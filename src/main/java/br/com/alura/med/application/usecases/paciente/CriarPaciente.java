package br.com.alura.med.application.usecases.paciente;

import br.com.alura.med.application.gateways.RepositorioPaciente;
import br.com.alura.med.domain.entities.paciente.Paciente;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CriarPaciente {

    private final RepositorioPaciente repositorio;

    public Paciente execute(Paciente paciente) {
        return repositorio.cadastrar(paciente);
    }
}
