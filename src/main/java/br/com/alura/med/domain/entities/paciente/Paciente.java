package br.com.alura.med.domain.entities.paciente;

import br.com.alura.med.domain.EnderecoValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Paciente {

    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private EnderecoValueObject endereco;
    private boolean ativo;
}
