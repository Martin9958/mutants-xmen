package com.mercadolibre.mutantsxmen.core.validator;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;

public interface DNAPetitionValidator {

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
