package br.com.alura.med.mock;

import static br.com.alura.med.domain.entities.medico.Especialidade.CARDIOLOGIA;

import br.com.alura.med.infra.controllers.requests.DadosCadastroMedico;
import br.com.alura.med.infra.controllers.requests.DadosEndereco;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DadosCadastroMedicoMock {

    public DadosCadastroMedico newDados() {
        return new DadosCadastroMedico("Nome", "email@email.com", "11 1234-5678",
                "123456", CARDIOLOGIA, new DadosEndereco(
                "Rua", "Bairro", "01234567", "São Paulo", "SP", "32A", "123"));
    }
}
