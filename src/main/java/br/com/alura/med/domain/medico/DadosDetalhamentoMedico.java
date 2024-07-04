package br.com.alura.med.domain.medico;

import br.com.alura.med.domain.endereco.Endereco;
import io.swagger.v3.oas.annotations.media.Schema;

public record DadosDetalhamentoMedico(
        @Schema(description = "Nome do médico", example = "Dr. José da Silva")
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        Endereco dadosEndereco) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(),
                medico.getEndereco());
    }

}
