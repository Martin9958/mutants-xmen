package com.mercadolibre.mutantsxmen.core.validator.util;

/**
 * Validation Messages Fields
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public class ValidationMessageField {

    /** The Common Message in the Validation Field */
    public static final String COMMON_MESSAGE = "The DNA Code isn't correct : ";

    /** Message when the Dna Request present wrong elements */
    public static final String DNA_REQUEST_MESSAGE =  COMMON_MESSAGE + "Object received is ";

    /**  Message when the Dna Matrix isn't square */
    public static final String DNA_MATRIX_SQUARE_MESSAGE = COMMON_MESSAGE + "because the dna Matrix haven't the same size in rows and columns.";

    /**  Message when the Dna Matrix Nitrogen Bases Number is Invalid */
    public static final String DNA_MATRIX_NITROGEN_BASES_VALID_NUMBER_MESSAGE = COMMON_MESSAGE + "because the number of nitrogen bases and sequences are less than ";

    /**  Message when the Dna Matrix Contains other character to Nitrogen Bases  */
    public static final String DNA_MATRIX_CORRECT_NITROGEN_BASES_MESSAGE = COMMON_MESSAGE + "because the sequence contains incorrect nitrogen Base ->  ";

}
