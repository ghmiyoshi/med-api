package br.com.alura.med.naousar.domain.medico;

import br.com.alura.med.domain.entities.medico.Especialidade;

public record DadosListagemMedico(String nome,
                                  String email,
                                  String crm,
                                  Especialidade especialidade) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }

}
