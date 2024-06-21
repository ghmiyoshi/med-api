package br.com.alura.med.application.usecases.medico;

import br.com.alura.med.application.gateways.RepositorioMedico;
import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.domain.entities.medico.Medico;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BuscarMedico {

    private final RepositorioMedico repositorio;

    public Medico execute(Long id) {
        return repositorio.buscar(id);
    }

    public Medico execute(Especialidade especialidade, LocalDateTime data) {
        return repositorio.buscarMedicoAleatorioLivreNaData(especialidade, data);
    }
}
