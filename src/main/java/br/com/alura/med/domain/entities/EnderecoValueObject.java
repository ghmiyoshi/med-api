package br.com.alura.med.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/* Entity: Posso ter dois medicos com o nome Gabriel Souza mas não são os mesmos, eles possuem
identificadores diferentes e únicos.

Value object: Quando tenho o mesmo bairro, cep, cidade, o conjunto de informações faz com que
sejam o mesmo. Não possuem identificadores únicos. */
@Getter
@Setter
@AllArgsConstructor
public class EnderecoValueObject {

    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;
}
