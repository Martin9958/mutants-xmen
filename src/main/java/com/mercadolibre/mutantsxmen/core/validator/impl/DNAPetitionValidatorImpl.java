package com.mercadolibre.mutantsxmen.core.validator.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.DNAPetitionValidator;
import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.core.validator.exception.base.ExceptionTypesEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class DNAPetitionValidatorImpl implements DNAPetitionValidator {

    /** */
    private static final Integer NITROGEN_BASE_NUMBER = 4;

    /** */
    private final List<String> witheList = Arrays.asList("A", "C", "G", "T");

    /** {@inheritDoc} */
    @Override
    public void validateDNACodeRequest(List<String> dna) throws DNAValidationException {

        if(dna == null || dna.contains(null) || dna.isEmpty()){
            throw new DNAValidationException(ExceptionTypesEnum.DNA_VALIDATION_ERROR,
                                             HttpStatus.BAD_REQUEST,
                                             "The DNA Code isn't correct : " + dna);
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
            throw new DNAValidationException(ExceptionTypesEnum.DNA_VALIDATION_ERROR,
                    HttpStatus.BAD_REQUEST,
                    "The DNA sequence isn't correct, because the dna Matrix haven't the same size in rows and columns");
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
            throw new DNAValidationException(ExceptionTypesEnum.DNA_VALIDATION_ERROR,
                    HttpStatus.BAD_REQUEST,
                    "The DNA sequence isn't correct, because the number of nitrogen bases and sequences are less tan 4");
        }else{
            for(String nitrogenBase : dnaSequence){
                if(!witheList.contains(nitrogenBase)){
                    throw new DNAValidationException(ExceptionTypesEnum.DNA_VALIDATION_ERROR,
                            HttpStatus.BAD_REQUEST,
                            "The DNA sequence isn't correct, because the sequence contains incorrect nitrogen Base : " + nitrogenBase);
                }
            }
        }

    }

}
