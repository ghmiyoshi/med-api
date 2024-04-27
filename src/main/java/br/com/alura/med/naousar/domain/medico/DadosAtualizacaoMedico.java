package br.com.alura.med.naousar.domain.medico;

import br.com.alura.med.naousar.domain.endereco.DadosEndereco;
import br.com.alura.med.naousar.domain.utils.ObjectMapperUtils;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoMedico(@NotNull Long id,
                                     String nome,
                                     String telefone,
                                     DadosEndereco endereco) {

    @Override
    public String toString() {
        return ObjectMapperUtils.writeObjectInJson(this);
    }

}
