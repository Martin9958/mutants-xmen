package com.mercadolibre.mutantsxmen.core.validator;

import java.util.ArrayList;

/**
 *
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface BrotherhoodRecruiterValidator {

    /**
     *
     * @param dnaMatrix
     * @return
     */
    boolean detectGenX(ArrayList<ArrayList<String>> dnaMatrix);

}
