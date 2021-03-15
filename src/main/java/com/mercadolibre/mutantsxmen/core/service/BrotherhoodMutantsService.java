package com.mercadolibre.mutantsxmen.core.service;

import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;

/**
 * Brotherhood Mutants Service offered to the recruiter process for new mutants
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface BrotherhoodMutantsService {

    /**
     * This method validate if the DNA received is from a mutant or a human
     *
     * @param dna the DNA Code received from the recruiter
     * @return True if the DNA received is from a Mutant - False if the DNA received is from a Human
     */
    boolean isMutant(List<String> dna) throws DNAValidationException;

    /**
     * This method get the Recruiter Mutants Statics
     *
     * @return the Recruiter Statics from the registered dna sequences
     */
    RecruiterStatisticsResponse getRecruiterMutantStatics();

}
