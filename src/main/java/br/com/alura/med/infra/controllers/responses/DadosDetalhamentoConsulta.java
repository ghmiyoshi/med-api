package br.com.alura.med.infra.controllers.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(String nomeMedico,
                                        String nomePaciente,
                                        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                                        LocalDateTime data) {
}
