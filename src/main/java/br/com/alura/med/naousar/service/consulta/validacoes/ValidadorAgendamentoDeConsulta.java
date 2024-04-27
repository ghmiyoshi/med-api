package br.com.alura.med.naousar.service.consulta.validacoes;

import br.com.alura.med.naousar.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(final DadosAgendamentoConsulta dados);

}
