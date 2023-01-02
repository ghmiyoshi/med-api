package br.com.alura.med.model.record;

import br.com.alura.med.model.Especialidade;
import br.com.alura.med.model.Medico;

public record DadosListagemMedico(
        String nome, String email, String crm, Especialidade especialidade
) {
    public DadosListagemMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
