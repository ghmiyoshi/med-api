package br.com.alura.med.infra.controllers.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long idMedico,
                                        Long idPaciente,
                                        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                                        LocalDateTime data,
                                        String mensagem) {

//    public DadosDetalhamentoConsulta(ConsultaEntity consulta) {
//        this(consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(),
//                "Consulta agendada com"
//                        + " sucesso");
//    }

}
