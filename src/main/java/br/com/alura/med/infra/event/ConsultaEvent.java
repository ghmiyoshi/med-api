package br.com.alura.med.infra.event;

import br.com.alura.med.infra.persistence.consulta.ConsultaEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConsultaEvent {

    private ConsultaEntity consulta;
}
