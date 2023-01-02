package br.com.alura.med.model.record;

import br.com.alura.med.model.Especialidade;
import br.com.alura.med.model.Medico;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record DadosCadastroMedico(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull @Valid
        DadosEndereco endereco) {
    public Medico converterParaMedico() {
        return new Medico(nome, email, telefone, crm, especialidade, endereco.converterParaEndereco());
    }

}
