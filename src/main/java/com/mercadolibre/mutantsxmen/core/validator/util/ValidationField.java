package com.mercadolibre.mutantsxmen.core.validator.util;

/**
 *
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public class ValidationField {

    /**  */
    public static final String COMMON_MESSAGE = "The DNA Code isn't correct : ";

    /**  */
    public static final String DNA_REQUEST_MESSAGE =  COMMON_MESSAGE + "Object received is ";

    /**  */
    public static final String DNA_MATRIX_SQUARE_MESSAGE = COMMON_MESSAGE + "because the dna Matrix haven't the same size in rows and columns.";

    /**  */
    public static final String DNA_MATRIX_NITROGEN_BASES_VALID_NUMBER_MESSAGE = COMMON_MESSAGE + "because the number of nitrogen bases and sequences are less than ";

    /**  */
    public static final String DNA_MATRIX_CORRECT_NITROGEN_BASES_MESSAGE = COMMON_MESSAGE + "because the sequence contains incorrect nitrogen Base ->  ";

}
