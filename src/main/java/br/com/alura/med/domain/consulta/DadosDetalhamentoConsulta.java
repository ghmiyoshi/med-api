package br.com.alura.med.domain.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(Long idMedico,
                                        Long idPaciente,
                                        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
                                        LocalDateTime data,
                                        String mensagem) {

    public DadosDetalhamentoConsulta(Consulta consulta) {
        this(consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData(), "Consulta agendada com"
                + " sucesso");
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
