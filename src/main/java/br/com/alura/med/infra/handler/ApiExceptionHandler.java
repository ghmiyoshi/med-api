package br.com.alura.med.infra.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                                                                           exception.getMessage()));
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

}
