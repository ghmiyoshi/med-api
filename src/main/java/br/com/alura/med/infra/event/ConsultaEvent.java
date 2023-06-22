package br.com.alura.med.infra.event;

import br.com.alura.med.domain.consulta.Consulta;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConsultaEvent {

    private Consulta consulta;

}
