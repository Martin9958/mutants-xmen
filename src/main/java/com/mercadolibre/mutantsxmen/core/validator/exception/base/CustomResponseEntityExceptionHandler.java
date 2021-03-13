package com.mercadolibre.mutantsxmen.core.validator.exception.base;

import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /** Logger */
    private static final Logger LOGGER = LogManager.getLogger(CustomResponseEntityExceptionHandler.class);

    /**
     * Customize the response for EntityNotValidException.
     * <p>This method delegates to {@link #handleExceptionInternal}.
     * @param exception the exception
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler({DNAValidationException.class})
    public ResponseEntity<Object> handleDNAException(
            final DNAValidationException exception, final WebRequest request) {

        HttpStatus status = getHttpStatusFromException(exception);
        ErrorResponseBody errorResponseBody = exception.getResponseBody().initialize(exception, status, request);
        logError(errorResponseBody, exception);

        return new ResponseEntity<>(errorResponseBody, status);
    }

    /**
     * Get {@link HttpStatus} from {@link DNAValidationException}
     *
     * @param exception {@link DNAValidationException}
     * @return {@link HttpStatus}
     */
    private HttpStatus getHttpStatusFromException(final DNAValidationException exception) {

        HttpStatus status = getResponseStatusFromAnnotation(exception.getClass());
        if(status == null) {
            try {
                return HttpStatus.valueOf(exception.getResponseBody().getError().name());
            } catch(IllegalArgumentException iae) {
                return HttpStatus.CONFLICT;
            }
        }
        return status;
    }

    /**
     * Get {@link HttpStatus} from {@link ResponseStatus} annotation
     *
     * @param reflectClass Exception class
     * @return {@link HttpStatus} or null if the class has no annotation
     */
    private HttpStatus getResponseStatusFromAnnotation(final Class<?> reflectClass) {

        if (reflectClass.isAnnotationPresent(ResponseStatus.class)) {
            ResponseStatus responseStatus = reflectClass.getAnnotation(ResponseStatus.class);
            return responseStatus.value();
        }
        return null;
    }

    /**
     * Log the errors returned in the error response body
     *
     * @param errorResponseBody Error response body
     * @param ex Thrown exception
     */
    private void logError(ErrorResponseBody errorResponseBody, Exception ex) {
        LOGGER.error(errorResponseBody.getTrace(), ex);
    }


}
