package com.mercadolibre.mutantsxmen.core.validator.exception.base;

import java.time.LocalDateTime;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@Data
@ApiModel(description = "All details about the error response. ")
public class ErrorResponseBody {

    /** Time when the exception is thrown */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(position = 1, notes = "Date and time of the error. ", example = "2020-07-24 05:49:07")
    private LocalDateTime timestamp;

    /** Exception status */
    @ApiModelProperty(position = 2, notes = "Error code. ", example = "400")
    private Integer status;

    /** Error message */
    @ApiModelProperty(position = 3, notes = "Error name. ", example = "BAD_REQUEST")
    private HttpStatus error;

    /** Controller path */
    @ApiModelProperty(position = 5, notes = "Invoked path. ", example = "/supplier-bank-account")
    private String path;

    /** Custom error type */
    @ApiModelProperty(position = 6, notes = "Specific type of error. ", example = "VALIDATION_ERROR")
    private ExceptionTypesEnum type;

    /** Custom error message with method information */
    @ApiModelProperty(position = 7, notes = "Error message. ", example = "Validation error during request")
    private String message;

    /** Error details */
    @JsonIgnore
    private String detail;

    /** Error trace */
    @JsonIgnore
    private String trace;

    /**
     * {@link ErrorResponseBody} constructor
     *
     * @param type exception type
     * @param message message
     */
    public ErrorResponseBody(final ExceptionTypesEnum type, HttpStatus errorEnum, final String message) {

        this.type = type;
        this.message = message;
        this.error = errorEnum;

    }

    /**
     * Build an {@link ErrorResponseBody}
     *
     * @param exception General exception thrown
     * @param httpStatus http status
     * @param request web request
     */
    public ErrorResponseBody initialize(final Exception exception, final HttpStatus httpStatus,
                                        final WebRequest request) {

        this.setTimestamp(LocalDateTime.now());
        this.setDetail(exception.getMessage());
        this.setTrace(Arrays.toString(exception.getStackTrace()));
        this.setStatus(httpStatus.value());
        this.setError(httpStatus);
        this.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        return this;
    }

}
