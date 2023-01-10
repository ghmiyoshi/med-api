package br.com.alura.med.infra.handler;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
public class ApiError {
    @Builder.Default
    private OffsetDateTime timestamp = OffsetDateTime.now();
    private HttpStatus status;
    private String error;
    private List<ApiErrorField> errors;

    @Getter
    @Builder
    static class ApiErrorField {
        private String field;
        private String message;
    }

}
