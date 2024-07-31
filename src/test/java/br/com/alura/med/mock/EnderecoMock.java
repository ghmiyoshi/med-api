package br.com.alura.med.mock;

import br.com.alura.med.domain.EnderecoValueObject;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EnderecoMock {

    public EnderecoValueObject newEndereco() {
        return new EnderecoValueObject("Rua", "Bairro", "01234567", "São Paulo", "SP",
                "32A", "123");
    }
}
