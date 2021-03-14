package com.mercadolibre.mutantsxmen.core.validator.impl;

import java.util.ArrayList;

import com.mercadolibre.mutantsxmen.core.validator.BrotherhoodRecruiterValidator;
import com.mercadolibre.mutantsxmen.core.validator.util.MoveValidationEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@Component
public class BrotherHoodRecruiterValidatorImpl implements BrotherhoodRecruiterValidator {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(BrotherHoodRecruiterValidatorImpl.class);

    /** {@inheritDoc} */
    @Override
    public boolean detectGenX(ArrayList<ArrayList<String>> dnaMatrix) {

        int equalSequence = 0;
        for(int i = 0; i < dnaMatrix.size(); i++){
            for (int j = 0 ; j < dnaMatrix.get(i).size(); j++){
                if(j < dnaMatrix.get(i).size() - 3) {
                    if(validateInDirection(MoveValidationEnum.HORIZONTAL, dnaMatrix, i, j)){
                        equalSequence ++;
                    }
                }
                if(i < dnaMatrix.size() - 3) {
                    if(validateInDirection(MoveValidationEnum.VERTICAL, dnaMatrix, i, j)){
                        equalSequence ++;
                    }
                }
                if((i < dnaMatrix.size() - 3) && (j < dnaMatrix.get(i).size() - 3)) {
                    if(validateInDirection(MoveValidationEnum.DIAGONAL, dnaMatrix, i, j)){
                        equalSequence ++;
                    }
                }
                if((i >= 3) && (j < dnaMatrix.get(i).size() - 3)) {
                    if(validateInDirection(MoveValidationEnum.INVERSE_DIAGONAL, dnaMatrix, i, j)){
                        equalSequence ++;
                    }
                }
            }
        }

        LOGGER.info("Similar Sequences Found In the DNA Code: {}", equalSequence);
        return equalSequence >= 2;

    }

    /**
     *
     * @param move
     * @param dnaMatrix
     * @param i
     * @param j
     * @return
     */
    private boolean validateInDirection(MoveValidationEnum move, ArrayList<ArrayList<String>> dnaMatrix, Integer i , Integer j){

        boolean validate;

        switch (move){
            case HORIZONTAL:
                validate = validation(dnaMatrix.get(i).get(j), dnaMatrix.get(i).get(j+1),
                                      dnaMatrix.get(i).get(j+2), dnaMatrix.get(i).get(j+3));
                break;
            case VERTICAL:
                validate = validation(dnaMatrix.get(i).get(j), dnaMatrix.get(i+1).get(j),
                                      dnaMatrix.get(i+2).get(j), dnaMatrix.get(i+3).get(j));
                break;
            case DIAGONAL:
                validate = validation(dnaMatrix.get(i).get(j), dnaMatrix.get(i+1).get(j+1),
                                      dnaMatrix.get(i+2).get(j+2), dnaMatrix.get(i+3).get(j+3));
                break;
            case INVERSE_DIAGONAL:
                validate = validation(dnaMatrix.get(i).get(j), dnaMatrix.get(i-1).get(j+1),
                                      dnaMatrix.get(i-2).get(j+2), dnaMatrix.get(i-3).get(j+3));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + move);
        }

        return validate;

    }

    /**
     *
     * @param nitrogenBaseA
     * @param nitrogenBaseB
     * @param nitrogenBaseC
     * @param nitrogenBaseD
     * @return
     */
    private boolean validation(String nitrogenBaseA,String nitrogenBaseB,String nitrogenBaseC,String nitrogenBaseD){

        return  nitrogenBaseA.equals(nitrogenBaseB)
                && nitrogenBaseB.equals(nitrogenBaseC)
                && nitrogenBaseC.equals(nitrogenBaseD);

    }

}
