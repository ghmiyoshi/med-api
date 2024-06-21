package br.com.alura.med.mock;

import br.com.alura.med.domain.entities.consulta.Consulta;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ConsultaMock {

    public Consulta newConsulta() {
        return new Consulta(MedicoMock.newMedico(), PacienteMock.newPaciente(),
                LocalDateTime.now().plusHours(1));
    }
}
