package br.com.alura.med.domain.entities.medico;

import br.com.alura.med.domain.EnderecoValueObject;

public class FabricaDeMedico {

    private Medico medico;

    public Medico novo(String nome, String email, String telefone, String crm,
                       Especialidade especialidade,
                       EnderecoValueObject domain) {
        return new Medico(nome, email, telefone, crm, especialidade, domain, true);
    }

    public Medico comNomeEmailTelefone(String nome, String email, String telefone) {
        this.medico = new Medico(nome, email, telefone, null, null, null, true);
        return this.medico;
    }

    public Medico incluirEndereco(String logradouro, String bairro, String cep, String cidade,
                                  String uf, String complemento, String numero) {
        this.medico.setEndereco(new EnderecoValueObject(logradouro, bairro, cep, cidade, uf,
                complemento, numero));
        return this.medico;
    }

    public Medico comNomeTelefoneEndereco(String nome, String telefone,
                                          EnderecoValueObject domain) {
        this.medico = comNomeEmailTelefone(nome, null, telefone);
        this.incluirEndereco(domain.getLogradouro(), domain.getBairro(), domain.getCep(),
                domain.getCidade(),
                domain.getUf(), domain.getComplemento(), domain.getNumero());
        return this.medico;
    }
}
