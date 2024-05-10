package br.com.alura.med.application.usecases;

import br.com.alura.med.application.gateways.RepositorioDeMedico;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExcluirMedico {

    private final RepositorioDeMedico repositorio;

    public void execute(Long id) {
        repositorio.excluirMedico(id);
    }
}
