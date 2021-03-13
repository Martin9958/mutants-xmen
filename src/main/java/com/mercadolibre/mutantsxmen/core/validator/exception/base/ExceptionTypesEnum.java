package com.mercadolibre.mutantsxmen.core.validator.exception.base;

public enum ExceptionTypesEnum {

    /** General exception types */
    DNA_VALIDATION_ERROR("Validation error on the request body"),
    MESSAGE_NOT_READABLE("The request body is not readable"),
    CONFLICT_DATA_SOURCE("Conflict with data source"),

    /** WebHook exception types */
    DNA_CODE_ALREADY_EXIST("DNA Code Already exists");

    /** Exception type string representation  **/
    private String detail;

    /**
     * Constructor with description injected by dependency inversion
     *
     * @param detail exception string representation
     */
    ExceptionTypesEnum(String detail) {

        this.detail = detail;
    }

    /**
     *  Get exception string detail
     *
     * @return string description
     */
    public String detail() {

        return this.detail;
    }

}
