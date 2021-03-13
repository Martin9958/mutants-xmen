package com.mercadolibre.mutantsxmen.core.validator.impl;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.BrotherhoodRecruiterValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BrotherHoodRecruiterValidatorImpl implements BrotherhoodRecruiterValidator {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(BrotherHoodRecruiterValidatorImpl.class);

    /** {@inheritDoc} */
    @Override
    public boolean existsASimilarSequenceRecorded(List<String> dna) {
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean detectGenX(ArrayList<ArrayList<String>> dnaMatrix) {

        int equalSequence = 0;
        for(int i = 0; i < dnaMatrix.size(); i++){
            for (int j = 0 ; j < dnaMatrix.get(i).size(); j++){

                if(j < dnaMatrix.get(i).size() - 3) {
                    if(dnaMatrix.get(i).get(j).equals(dnaMatrix.get(i).get(j + 1))
                            && dnaMatrix.get(i).get(j + 1).equals(dnaMatrix.get(i).get(j + 2))
                            && dnaMatrix.get(i).get(j + 2).equals(dnaMatrix.get(i).get(j + 3))){
                        equalSequence ++;
                    }
                }

                if(i < dnaMatrix.size() - 3) {
                    if(dnaMatrix.get(i).get(j).equals(dnaMatrix.get(i + 1).get(j))
                            && dnaMatrix.get(i + 1).get(j).equals(dnaMatrix.get(i + 2).get(j))
                            && dnaMatrix.get(i + 2).get(j).equals(dnaMatrix.get(i + 3).get(j))){
                        equalSequence ++;
                    }
                }

                if((i < dnaMatrix.size() - 3) && (j < dnaMatrix.get(i).size() - 3)) {
                    if(dnaMatrix.get(i).get(j).equals(dnaMatrix.get(i+1).get(j+1))
                            && dnaMatrix.get(i + 1).get(j + 1).equals(dnaMatrix.get(i + 2).get(j + 2))
                            && dnaMatrix.get(i + 2).get(j + 2).equals(dnaMatrix.get(i + 3).get(j + 3))){
                        equalSequence ++;
                    }
                }

                if((i >= 3) && (j < dnaMatrix.get(i).size() - 3)) {
                    if(dnaMatrix.get(i).get(j).equals(dnaMatrix.get(i - 1).get(j + 1))
                            && dnaMatrix.get(i - 1).get(j + 1).equals(dnaMatrix.get(i - 2).get(j + 2))
                            && dnaMatrix.get(i - 2).get(j + 2).equals(dnaMatrix.get(i - 3).get(j + 3))){
                        equalSequence ++;
                    }
                }

            }
        }

        LOGGER.info("Similar Sequences Found In the DNA Code: {}", equalSequence);
        return equalSequence >= 2;

    }

}
