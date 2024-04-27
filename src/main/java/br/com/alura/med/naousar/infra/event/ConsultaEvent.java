package br.com.alura.med.naousar.infra.event;

import br.com.alura.med.naousar.domain.consulta.Consulta;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConsultaEvent {

    private Consulta consulta;

}
