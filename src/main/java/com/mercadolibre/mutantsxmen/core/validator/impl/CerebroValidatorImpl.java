package com.mercadolibre.mutantsxmen.core.validator.impl;

import static com.mercadolibre.mutantsxmen.core.validator.util.ValidationField.DNA_MATRIX_CORRECT_NITROGEN_BASES_MESSAGE;
import static com.mercadolibre.mutantsxmen.core.validator.util.ValidationField.DNA_MATRIX_NITROGEN_BASES_VALID_NUMBER_MESSAGE;
import static com.mercadolibre.mutantsxmen.core.validator.util.ValidationField.DNA_MATRIX_SQUARE_MESSAGE;
import static com.mercadolibre.mutantsxmen.core.validator.util.ValidationField.DNA_REQUEST_MESSAGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.CerebroValidator;
import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.core.validator.exception.base.ExceptionTypesEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@Component
public class CerebroValidatorImpl implements CerebroValidator {

    /** */
    private static final Integer NITROGEN_BASE_NUMBER = 4;

    /** */
    private final List<String> NITROGEN_BASE = Arrays.asList("A", "C", "G", "T");

    /** {@inheritDoc} */
    @Override
    public void validateDNACodeRequest(List<String> dna) throws DNAValidationException {

        if(dna == null || dna.contains(null) || dna.isEmpty()){
            throw new DNAValidationException(ExceptionTypesEnum.DNA_VALIDATION_ERROR,
                                             HttpStatus.BAD_REQUEST,
                                             DNA_REQUEST_MESSAGE + dna);
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
                                             DNA_MATRIX_SQUARE_MESSAGE);
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
                                             DNA_MATRIX_NITROGEN_BASES_VALID_NUMBER_MESSAGE + NITROGEN_BASE_NUMBER);

        }else{
            for(String nitrogenBase : dnaSequence){
                if(!NITROGEN_BASE.contains(nitrogenBase)){
                    throw new DNAValidationException(ExceptionTypesEnum.DNA_VALIDATION_ERROR,
                                                     HttpStatus.BAD_REQUEST,
                                                     DNA_MATRIX_CORRECT_NITROGEN_BASES_MESSAGE + nitrogenBase);
                }
            }

        }

    }

}
