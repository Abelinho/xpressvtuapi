package com.abel.airtimevtu.exceptions;

import com.abel.airtimevtu.exceptions.model.ErrorDetails;
import com.abel.airtimevtu.exceptions.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Date;

import static com.abel.airtimevtu.utility.AppUtility.sanitize;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public final ResponseEntity<ErrorResponse<ErrorDetails>> handleCustomException(WebClientResponseException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(true), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(errorDetails.getCode()).body(ErrorResponse.<ErrorDetails>builder()
                .message(ex.getMessage())
                .status(sanitize(errorDetails.getCode().name()))
                .error(errorDetails)
                .build());
    }

}
