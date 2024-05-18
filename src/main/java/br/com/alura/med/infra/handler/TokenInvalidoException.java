package br.com.alura.med.infra.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TokenInvalidoException extends ResponseStatusException {
    public TokenInvalidoException(String mensagem) {
        super(HttpStatus.UNAUTHORIZED, mensagem);
    }
}
