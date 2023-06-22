package br.com.alura.med.infra.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailService {

    @EventListener
    public void enviaNotificacaoPorEmail(final ConsultaEvent event) {
        log.info("""
                         Consulta do paciente %s agendada para o dia %s com o médico %s""".formatted(event.getConsulta().getPaciente().getNome(),
                                                                                                     event.getConsulta().getData(),
                                                                                                     event.getConsulta().getMedico().getNome()));
    }

}
