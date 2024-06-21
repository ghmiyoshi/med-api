package br.com.alura.med.mock;

import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.infra.persistence.medico.MedicoEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MedicoEntityMock {

    public MedicoEntity newMedico(String nome, String email, String crm,
                                  Especialidade especialidade) {
        return new MedicoEntity(nome, email, null, null, especialidade, null);
    }
}
