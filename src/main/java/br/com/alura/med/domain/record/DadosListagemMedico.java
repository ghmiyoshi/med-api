package br.com.alura.med.domain.record;

import br.com.alura.med.domain.model.Especialidade;
import br.com.alura.med.domain.model.Medico;

public record DadosListagemMedico(
        String nome, String email, String crm, Especialidade especialidade
) {
    public DadosListagemMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
