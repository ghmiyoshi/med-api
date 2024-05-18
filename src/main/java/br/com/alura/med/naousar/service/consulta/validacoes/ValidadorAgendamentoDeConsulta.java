package br.com.alura.med.naousar.service.consulta.validacoes;

import br.com.alura.med.infra.controllers.requests.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(final DadosAgendamentoConsulta dados);

}
