package br.com.alura.med.application.usecases;

import br.com.alura.med.application.gateways.RepositorioDeMedico;
import br.com.alura.med.domain.entities.medico.Medico;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CriarMedico {

    private final RepositorioDeMedico repositorio;

    public Medico cadastrarMedico(Medico medico) {
        return repositorio.cadastrarMedico(medico);
    }
}
