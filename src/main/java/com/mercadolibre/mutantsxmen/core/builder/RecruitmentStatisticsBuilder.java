package com.mercadolibre.mutantsxmen.core.builder;

import java.math.BigDecimal;

import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;

/**
 * Recruitment Statistics Builder: Used to build the stats Objects resulting from Cerebro processing
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface RecruitmentStatisticsBuilder {

    /**
     * Build the Stats From Cerebro Processing Analysis of the quantity of humans, mutants and others stats required
     *
     * @param totalMutants number of registered mutants
     * @param totalHumans number of registered humans
     * @param ratio proportion of mutants over the number of humans
     * @return the Stats From Cerebro Processing Analysis
     */
    RecruiterStatisticsResponse buildStatsFromAnalysis(Integer totalMutants, Integer totalHumans, BigDecimal ratio);

}
