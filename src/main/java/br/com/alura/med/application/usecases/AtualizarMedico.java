package br.com.alura.med.application.usecases;

import br.com.alura.med.application.gateways.RepositorioDeMedico;
import br.com.alura.med.domain.entities.medico.Medico;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AtualizarMedico {

    private final RepositorioDeMedico repositorio;

    public Medico execute(Long id, Medico medico) {
        return repositorio.atualizarMedico(id, medico);
    }
}
