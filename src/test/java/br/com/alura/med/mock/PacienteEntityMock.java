package br.com.alura.med.mock;

import br.com.alura.med.infra.persistence.paciente.PacienteEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PacienteEntityMock {

    public PacienteEntity newPaciente(String nome, String email, String cpf) {
        return new PacienteEntity(nome, email, null, cpf, null);
    }
}
