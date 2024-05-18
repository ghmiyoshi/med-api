package br.com.alura.med.infra.handler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ProblemDetail handleResponseStatusException(final ResponseStatusException exception) {
        return buildProblemDetail(exception);
    }

    private ProblemDetail buildProblemDetail(final ResponseStatusException exception) {
        var problemDetail = ProblemDetail.forStatusAndDetail(exception.getStatusCode(),
                exception.getReason());
        problemDetail.setType(URI.create(createUrl(exception.getStatusCode())));
        return problemDetail;
    }

    private ProblemDetail buildProblemDetailWithFieldErrors(final Exception exception,
                                                            final List<Map<String, String>> errorFields) {
        var problemDetail = buildProblemDetail(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Um ou mais campos inválidos. Verifique e tente novamente"));
        problemDetail.setProperty("errors", errorFields);
        return problemDetail;
    }

    private String createUrl(HttpStatusCode httpStatusCode) {
        return switch (httpStatusCode.value()) {
            case 400 -> "https://api.med.com/errors/bad-request";
            case 404 -> "https://api.med.com/errors/not-found";
            case 500 -> "https://api.med.com/errors/internal-server-error";
            default -> "https://api.med.com/errors";
        };
    }

    @ExceptionHandler(ValidacaoException.class)
    public ProblemDetail handleValidacaoException(final ValidacaoException exception) {
        return buildProblemDetail(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        if (fieldErrors.isEmpty()) {
            List<ObjectError> allErrors = exception.getAllErrors();
            List<Map<String, String>> apiErrorFields = allErrors.stream()
                    .map(fieldError -> {
                        Map<String, String> errorField = new HashMap<>();
                        errorField.put("message", fieldError.getDefaultMessage());
                        return errorField;
                    }).toList();


            return buildProblemDetailWithFieldErrors(exception, apiErrorFields);
        }

        List<Map<String, String>> apiErrorFields = fieldErrors.stream()
                .map(fieldError -> {
                    Map<String, String> errorField = new HashMap<>();
                    errorField.put("field", fieldError.getField());
                    errorField.put("message", fieldError.getDefaultMessage());
                    return errorField;
                }).toList();
        return buildProblemDetailWithFieldErrors(exception, apiErrorFields);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        Throwable rootCause = ExceptionUtils.getRootCause(exception);
        if (rootCause instanceof InvalidFormatException invalidFormatException) {
            return handleInvalidFormatException(invalidFormatException);
        } else if (rootCause instanceof UnrecognizedPropertyException unrecognizedPropertyException) {
            return buildProblemDetail(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    String.format("Invalid JSON body, property: %s",
                            unrecognizedPropertyException.getPropertyName())));
        } else if (rootCause instanceof PropertyBindingException propertyBindingException) {
            return handlePropertyBindingException(propertyBindingException);
        }
        return buildProblemDetail(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "O corpo da requisição está inválido. Verifique erro de sintaxe e tente " +
                        "novamente"));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthenticationException(final AuthenticationException exception) {
        return buildProblemDetail(new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                "Usuário inexistente ou senha inválida"));
    }

    @ExceptionHandler(TokenInvalidoException.class)
    public ProblemDetail handleTokenInvalidoException(final TokenInvalidoException exception) {
        return buildProblemDetail(new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                exception.getMessage()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ProblemDetail handlerNoHandlerFoundException(NoHandlerFoundException exception) {
        return buildProblemDetail(new ResponseStatusException(HttpStatus.NOT_FOUND,
                "O recurso que você tentou acessar não existe"));
    }

    private ProblemDetail handleInvalidFormatException(InvalidFormatException ex) {
        var path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        var detail = String.format("A propriedade '%s' recebeu o valor '%s' que é de um tipo " +
                        "inválido. Corrija e informe um valor compatível com o tipo %s", path,
                ex.getValue(),
                ex.getTargetType().getSimpleName());

        return buildProblemDetail(new ResponseStatusException(HttpStatus.BAD_REQUEST, detail));
    }

    private ProblemDetail handlePropertyBindingException(PropertyBindingException ex) {
        // Criei o método joinPath para reaproveitar em todos os métodos que precisam concatenar
        // os nomes das propriedades (separando por ".")
        var path = joinPath(ex.getPath());
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        String detail = null;

        if (rootCause instanceof IgnoredPropertyException) {
            detail = String.format("A propriedade '%s' não deve ser informada. Por favor remova " +
                    "essa propriedade e tente novamente", path);
        } else if (rootCause instanceof UnrecognizedPropertyException) {
            detail = String.format("A propriedade '%s' não existe. Corrija ou remova essa " +
                    "propriedade e tente novamente", path);
        }
        return buildProblemDetail(new ResponseStatusException(HttpStatus.BAD_REQUEST, detail));
    }

    private String joinPath(List<Reference> references) {
        return references.stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
    }
}
