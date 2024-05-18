package br.com.alura.med.application.usecases.paciente;

import br.com.alura.med.application.gateways.RepositorioPaciente;
import br.com.alura.med.domain.entities.paciente.Paciente;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarPaciente {

    private final RepositorioPaciente repositorio;

    public Paciente execute(Long id, Paciente paciente) {
        return repositorio.atualizarPaciente(id, paciente);
    }
}
