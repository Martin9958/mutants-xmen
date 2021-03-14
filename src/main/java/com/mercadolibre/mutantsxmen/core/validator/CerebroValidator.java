package com.mercadolibre.mutantsxmen.core.validator;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;

/**
 *
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface CerebroValidator {

    /**
     *
     * @param dna
     */
    void validateDNACodeMatrix(ArrayList<ArrayList<String>> dna) throws DNAValidationException;

    /**
     *
     * @param dna
     */
    void validateDNACodeRequest(List<String> dna) throws DNAValidationException;

}
