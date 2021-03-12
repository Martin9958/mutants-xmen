package com.mercadolibre.mutantsxmen.core.validator;

import java.util.ArrayList;
import java.util.List;

public interface BrotherhoodRecruiterValidator {

    /**
     *
     * @param dna
     * @return
     */
    boolean existsASimilarSequenceRecorded(List<String> dna);

    /**
     *
     * @param dnaMatrix
     * @return
     */
    boolean detectGenX(ArrayList<ArrayList<String>> dnaMatrix);

}
