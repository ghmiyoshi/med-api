package br.com.alura.med.domain.entities.medico;

import br.com.alura.med.domain.entities.EnderecoValueObject;

public class FabricaDeMedico {

    private Medico medico;

    public Medico comNomeEmailTelefone(String nome, String email, String telefone) {
        this.medico = new Medico(nome, email, telefone);
        return this.medico;
    }

    private Medico incluirEndereco(String logradouro, String bairro, String cep, String cidade,
                                   String uf, String complemento, String numero) {
        this.medico.setEndereco(new EnderecoValueObject(logradouro, bairro, cep, cidade, uf,
                complemento, numero));
        return this.medico;
    }
}
