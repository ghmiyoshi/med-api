package br.com.alura.med.mock;

import br.com.alura.med.infra.persistence.consulta.ConsultaEntity;
import br.com.alura.med.infra.persistence.medico.MedicoEntity;
import br.com.alura.med.infra.persistence.paciente.PacienteEntity;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConsultaEntityMock {

    public ConsultaEntity newConsulta(MedicoEntity medico, PacienteEntity paciente,
                                      LocalDateTime data) {
        return new ConsultaEntity(null, medico, paciente, data);
    }
}
