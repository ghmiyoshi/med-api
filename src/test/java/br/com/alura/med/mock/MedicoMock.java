package br.com.alura.med.mock;

import static br.com.alura.med.domain.entities.medico.Especialidade.CARDIOLOGIA;

import br.com.alura.med.domain.entities.medico.Medico;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MedicoMock {

    public Medico newMedico() {
        return new Medico("Nome", "Email", "Telefone", "Crm", CARDIOLOGIA, null, true);
    }
}
