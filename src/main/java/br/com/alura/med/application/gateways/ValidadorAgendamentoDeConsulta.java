package br.com.alura.med.application.gateways;

import br.com.alura.med.infra.controllers.requests.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(final DadosAgendamentoConsulta dados);

}
