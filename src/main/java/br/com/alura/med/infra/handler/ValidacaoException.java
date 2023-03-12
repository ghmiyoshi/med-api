package br.com.alura.med.infra.handler;

public class ValidacaoException extends RuntimeException {

    public ValidacaoException(final String mensagem) {
        super(mensagem);
    }

}
