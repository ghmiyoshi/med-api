package br.com.alura.med.domain.entities.medico;

import br.com.alura.med.domain.EnderecoValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Medico {

    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Especialidade especialidade;
    private EnderecoValueObject endereco;
    private boolean ativo;
}
