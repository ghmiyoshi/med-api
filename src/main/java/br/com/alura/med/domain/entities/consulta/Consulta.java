package br.com.alura.med.domain.entities.consulta;

import br.com.alura.med.domain.entities.medico.Medico;
import br.com.alura.med.domain.entities.paciente.Paciente;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Consulta {

    private Medico medico;
    private Paciente paciente;
    private LocalDateTime data;
}
