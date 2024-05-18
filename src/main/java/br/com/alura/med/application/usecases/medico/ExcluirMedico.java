package br.com.alura.med.application.usecases.medico;

import br.com.alura.med.application.gateways.RepositorioMedico;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExcluirMedico {

    private final RepositorioMedico repositorio;

    public void execute(Long id) {
        repositorio.excluirMedico(id);
    }
}
