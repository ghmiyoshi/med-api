package br.com.alura.med.domain.record;

import br.com.alura.med.domain.model.Endereco;
import br.com.alura.med.domain.model.Especialidade;
import br.com.alura.med.domain.model.Medico;

public record DadosDetalhamentoMedico(
        String nome, String email, String crm, Especialidade especialidade, Endereco dadosEndereco
) {
    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
