package com.oglcnarbc.advice;
import com.oglcnarbc.DeliveryApi;
import com.oglcnarbc.common.exception.ConflictException;
import com.oglcnarbc.common.exception.InvalidRequestException;
import com.oglcnarbc.common.exception.NoContentException;
import com.oglcnarbc.common.exception.UnProcessableEntitiyException;
import com.oglcnarbc.common.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice(annotations = DeliveryApi.class)
@Slf4j
public class BookStoreControllerAdvice {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidException(InvalidRequestException e) {
        log.error("BookStoreControllerAdvice handles InvalidRequestException", e);
        return new ResponseEntity<>(anErrorResponseBuilder(HttpStatus.BAD_REQUEST, e.getMessage()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(UnProcessableEntitiyException.class)
    public ResponseEntity<ErrorResponse> handleUnprocessableException(UnProcessableEntitiyException e) {
        log.error("BookStoreControllerAdvice handles handleUnprocessableException", e);
        return new ResponseEntity<>(anErrorResponseBuilder(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictFieldException(ConflictException e) {
        log.error("BookStoreControllerAdvice handles handleUnprocessableException", e);
        return new ResponseEntity<>(anErrorResponseBuilder(HttpStatus.CONFLICT, e.getMessage()), HttpStatus.CONFLICT);

    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ErrorResponse> noContentException(NoContentException e) {
        log.error("BookStoreControllerAdvice handlesNoContentExceptionException", e);
        return new ResponseEntity<>(anErrorResponseBuilder(HttpStatus.NO_CONTENT, e.getMessage()), HttpStatus.NO_CONTENT);

    }

    private ErrorResponse anErrorResponseBuilder(HttpStatus status, String cause) {

        return ErrorResponse.builder()
                .message(cause)
                .status(status.value())
                .timestamp(LocalDate.now())
                .build();
    }
}
