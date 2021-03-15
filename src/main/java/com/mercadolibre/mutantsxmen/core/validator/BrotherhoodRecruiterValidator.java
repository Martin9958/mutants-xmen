package com.mercadolibre.mutantsxmen.core.validator;

import java.util.ArrayList;

/**
 * Validator: Brother hood Recruiter used to detect the GenX
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface BrotherhoodRecruiterValidator {

    /**
     * This method detect The Gen X (More than a similar sequence of 4 nitrogenous bases in the DNA Matrix)
     *
     * @param dnaMatrix the matrix resulting from dividing each piece of DNA code for analysis
     * @return True if the Gen X is detected - False if the GenX is not present in tre DNA
     */
    boolean detectGenX(ArrayList<ArrayList<String>> dnaMatrix);

}
