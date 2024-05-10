package br.com.alura.med.infra.controllers.mappers;

import br.com.alura.med.domain.EnderecoValueObject;
import br.com.alura.med.infra.controllers.requests.DadosEndereco;
import br.com.alura.med.infra.persistence.medico.EnderecoEntity;

public class EnderecoMapper {

    public EnderecoEntity toEntity(EnderecoValueObject enderecoValueObject) {
        return new EnderecoEntity(enderecoValueObject.getLogradouro(),
                enderecoValueObject.getBairro(),
                enderecoValueObject.getCep(), enderecoValueObject.getCidade(),
                enderecoValueObject.getUf(),
                enderecoValueObject.getComplemento(), enderecoValueObject.getNumero());
    }

    public EnderecoValueObject toDomain(EnderecoEntity enderecoEntity) {
        return new EnderecoValueObject(enderecoEntity.getLogradouro(),
                enderecoEntity.getBairro(),
                enderecoEntity.getCep(), enderecoEntity.getCidade(),
                enderecoEntity.getUf(),
                enderecoEntity.getComplemento(), enderecoEntity.getNumero());
    }

    public EnderecoValueObject toDomain(DadosEndereco endereco) {
        return new EnderecoValueObject(endereco.logradouro(),
                endereco.bairro(),
                endereco.cep(), endereco.cidade(),
                endereco.uf(),
                endereco.complemento(), endereco.numero());
    }

    public DadosEndereco toResponse(EnderecoValueObject endereco) {
        return new DadosEndereco(endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCep(), endereco.getCidade(),
                endereco.getUf(),
                endereco.getComplemento(), endereco.getNumero());
    }

    public EnderecoEntity toEntity(DadosEndereco endereco) {
        return new EnderecoEntity(endereco.logradouro(),
                endereco.bairro(),
                endereco.cep(), endereco.cidade(),
                endereco.uf(),
                endereco.complemento(), endereco.numero());
    }
}