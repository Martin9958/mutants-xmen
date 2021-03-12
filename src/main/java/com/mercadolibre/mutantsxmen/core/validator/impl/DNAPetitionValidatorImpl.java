package com.mercadolibre.mutantsxmen.core.validator.impl;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.DNAPetitionValidator;
import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import org.springframework.stereotype.Component;

@Component
public class DNAPetitionValidatorImpl implements DNAPetitionValidator {

    /** */
    private static final Integer NITROGEN_BASE_NUMBER = 4;

    /** {@inheritDoc} */
    @Override
    public void validateDNACodeRequest(List<String> dna) throws DNAValidationException {

        if(dna == null || dna.contains(null) || dna.isEmpty()){
            throw new DNAValidationException("The DNA Code isn't correct : " + dna );
        }

    }

    /** {@inheritDoc} */
    @Override
    public void validateDNACodeMatrix(ArrayList<ArrayList<String>> dnaMatrix) throws DNAValidationException {

        for(ArrayList<String> dnaSequence : dnaMatrix){
            isASquareMatrix(dnaSequence, dnaMatrix.size());
            hasCorrectTheNitrogenBases(dnaSequence, dnaMatrix.size());
        }

    }

    /**
     *
     * @param dnaSequence
     * @param dnaMatrixSize
     * @throws DNAValidationException
     */
    private void isASquareMatrix(ArrayList<String> dnaSequence, Integer dnaMatrixSize) throws DNAValidationException {

        if (dnaMatrixSize != dnaSequence.size()) {
            throw new DNAValidationException("The DNA sequence isn't correct, because the dna Matrix haven't the same size in rows and columns");
        }

    }

    /**
     *
     * @param dnaSequence
     * @param dnaMatrixSize
     * @throws DNAValidationException
     */
    private void hasCorrectTheNitrogenBases(ArrayList<String> dnaSequence, Integer dnaMatrixSize) throws DNAValidationException {

        if (dnaMatrixSize < NITROGEN_BASE_NUMBER && dnaSequence.size() < NITROGEN_BASE_NUMBER) {
            throw new DNAValidationException("The DNA sequence isn't correct, because the number of nitrogen bases and sequences are less tan 4");
        }else if(!dnaSequence.contains("A") && !dnaSequence.contains("C") && !dnaSequence.contains("G") && !dnaSequence.contains("T")){
            throw new DNAValidationException("The DNA sequence isn't correct, because the sequence doesn't contains any nitrogen base");
        }

    }

}
