package com.mercadolibre.mutantsxmen.core.service;

import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;

/**
 *
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface BrotherhoodMutantsService {

    /**
     *
     * @param dna
     * @return
     */
    boolean isMutant(List<String> dna) throws DNAValidationException;

    /**
     *
     * @return
     */
    RecruiterStatisticsResponse getRecruiterMutantStatics();

}
