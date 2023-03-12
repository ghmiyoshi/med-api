package br.com.alura.med.service.consulta.validacoes;

import br.com.alura.med.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(final DadosAgendamentoConsulta dados);

}
