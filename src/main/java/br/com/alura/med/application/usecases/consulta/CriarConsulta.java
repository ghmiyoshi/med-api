package br.com.alura.med.application.usecases.consulta;

import br.com.alura.med.application.gateways.RepositorioConsulta;
import br.com.alura.med.domain.entities.consulta.Consulta;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.domain.entities.paciente.Paciente;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CriarConsulta {

    private final RepositorioConsulta repositorio;

    public Consulta execute(final Medico medico, final Paciente paciente,
                            final LocalDateTime data) {
        return repositorio.cadastrar(medico, paciente, data);
    }
}
