package br.com.alura.med.mock;

import static br.com.alura.med.domain.entities.medico.Especialidade.CARDIOLOGIA;

import br.com.alura.med.domain.entities.medico.Medico;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MedicoMock {

    public Medico newMedico() {
        return new Medico("Nome", "email@email.com", "Telefone", "123456", CARDIOLOGIA,
                EnderecoMock.newEndereco(), true);
    }
}
