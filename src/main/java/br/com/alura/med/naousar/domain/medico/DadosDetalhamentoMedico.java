package br.com.alura.med.naousar.domain.medico;

import br.com.alura.med.naousar.domain.endereco.Endereco;

public record DadosDetalhamentoMedico(String nome,
                                      String email,
                                      String crm,
                                      Especialidade especialidade,
                                      Endereco dadosEndereco) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(),
                medico.getEndereco());
    }

}
