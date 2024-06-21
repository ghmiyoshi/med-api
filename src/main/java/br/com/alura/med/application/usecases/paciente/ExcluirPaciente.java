package br.com.alura.med.application.usecases.paciente;

import br.com.alura.med.application.gateways.RepositorioPaciente;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExcluirPaciente {

    private final RepositorioPaciente repositorio;

    public void execute(Long id) {
        repositorio.excluir(id);
    }
}
