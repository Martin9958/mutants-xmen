package com.mercadolibre.mutantsxmen.core.service;

import java.util.List;

import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;

public interface MutantsService {

    /**
     *
     * @param dna
     * @return
     */
    boolean isMutant(List<String> dna);

    /**
     *
     * @return
     */
    RecruiterStatisticsResponse getRecruiterMutantStatics();


}
