package br.com.alura.med.infra.controllers.requests;

import br.com.alura.med.domain.entities.medico.Especialidade;
import br.com.alura.med.infra.utils.ObjectMapperUtils;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/* @JsonAlias("produto_id") mapeia "apelidos" alternativos para os campos que serão recebidos do
JSON */
public record DadosAgendamentoConsulta(@JsonAlias("id_medico") Long idMedico,
                                       @JsonProperty("id_paciente") @NotNull Long idPaciente,
                                       @NotNull
                                       // padrão de data esperado
                                       @Future
                                       @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") // indico o
                                       LocalDateTime data,
                                       Especialidade especialidade) {
    @Override
    public String toString() {
        return ObjectMapperUtils.writeObjectInJson(this);
    }
}
