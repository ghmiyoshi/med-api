package br.com.alura.med.mock;

import br.com.alura.med.domain.entities.paciente.Paciente;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PacienteMock {

    public Paciente newPaciente() {
        return new Paciente("Nome", "Email", "Cpf", "Telefone", EnderecoMock.newEndereco(), true);
    }
}
