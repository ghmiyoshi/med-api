package br.com.alura.med.infra.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ValidacaoException extends ResponseStatusException {

    public ValidacaoException(final String mensagem) {
        super(HttpStatus.BAD_REQUEST, mensagem);
    }

}
