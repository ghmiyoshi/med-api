package br.com.alura.med.infra.controllers.requests;

import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.infra.utils.ObjectMapperUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(@NotBlank(message = "Nome é obrigatório") String nome,
                                  @NotBlank(message = "Email é obrigatório")
                                  @Email(message = "Formato do email é inválido")
                                  String email,
                                  @NotBlank(message = "Telefone é obrigatório")
                                  String telefone,
                                  @NotBlank(message = "CRM é obrigatório")
                                  @Pattern(regexp = "\\d{4,6}", message = "{crm.invalido}")
                                  String crm,
                                  @NotNull(message = "{especialidade.obrigatoria}")
                                  Especialidade especialidade,
                                  @NotNull(message = "Dados do endereço são obrigatórios")
                                  @Valid DadosEndereco endereco) {
    @Override
    public String toString() {
        return ObjectMapperUtils.writeObjectInJson(this);
    }
}
