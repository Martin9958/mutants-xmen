package com.mercadolibre.mutantsxmen.core.validator.exception;

import com.mercadolibre.mutantsxmen.core.validator.exception.base.ErrorResponseBody;
import com.mercadolibre.mutantsxmen.core.validator.exception.base.ExceptionTypesEnum;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class DNAValidationException extends Exception{

    private ErrorResponseBody responseBody;

    /**
     * Override super constructor with message
     *
     * @param exceptionType Exception type
     * @param message Describes the error message
     */
    public DNAValidationException(final ExceptionTypesEnum exceptionType, HttpStatus errorEnum, final String message) {

        super(message);
        responseBody = new ErrorResponseBody(exceptionType,errorEnum,message);
    }

}
