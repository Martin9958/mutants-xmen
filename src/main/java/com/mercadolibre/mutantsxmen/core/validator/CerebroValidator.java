package com.mercadolibre.mutantsxmen.core.validator;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;

/**
 * Cerebro Validator: exposed to validate the Received DNA Code and it's variations
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface CerebroValidator {

    /**
     * This method validate the DNA Code Matrix to see if it meets the correct specifications for the process
     *
     * @param dna the DNA Code Matrix to validate
     */
    void validateDNACodeMatrix(ArrayList<ArrayList<String>> dna) throws DNAValidationException;

    /**
     * This method validate the DNA Code Received to see if it meets the correct specifications for the process
     *
     * @param dna The DNA Received by the recruiter
     */
    void validateDNACodeRequest(List<String> dna) throws DNAValidationException;

}
