package br.com.alura.med.infra.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(final EntityNotFoundException exception) {
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(ApiError.builder()
                                                          .status(status)
                                                          .error(exception.getMessage())
                                                          .build());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<ApiError> handleValidacaoException(final ValidacaoException exception) {
        var status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(ApiError.builder()
                                                          .status(status)
                                                          .error(exception.getMessage())
                                                          .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getFieldErrors();
        var status = HttpStatus.BAD_REQUEST;

        if (fieldErrors.isEmpty()) {
            List<ObjectError> allErrors = exception.getAllErrors();
            List<ApiError.ApiErrorField> apiErrorFields = allErrors.stream().map(
                            fieldError -> ApiError.ApiErrorField.builder()
                                    .message(fieldError.getDefaultMessage())
                                    .build())
                    .toList();

            return ResponseEntity.status(status).body(ApiError.builder().status(status).errors(apiErrorFields).build());
        }

        List<ApiError.ApiErrorField> apiErrorFields = fieldErrors.stream().map(
                        fieldError -> ApiError.ApiErrorField.builder()
                                .field(fieldError.getField())
                                .message(fieldError.getDefaultMessage())
                                .build())
                .toList();

        return ResponseEntity.status(status).body(ApiError.builder().status(status).errors(apiErrorFields).build());
    }

}
