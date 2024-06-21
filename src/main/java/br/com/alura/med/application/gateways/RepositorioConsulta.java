package br.com.alura.med.application.gateways;

import br.com.alura.med.domain.entities.consulta.Consulta;
import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.domain.entities.paciente.Paciente;
import java.time.LocalDateTime;

public interface RepositorioConsulta {

    Consulta cadastrar(Medico medico, Paciente paciente, LocalDateTime data);

    Consulta buscar(Long id);
}