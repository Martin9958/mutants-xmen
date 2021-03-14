package com.mercadolibre.mutantsxmen.core.builder;

import java.math.BigDecimal;

import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;

/**
 *
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface RecruitmentStatisticsBuilder {

    /**
     *
     * @param totalMutants
     * @param totalHumans
     * @param ratio
     * @return
     */
    RecruiterStatisticsResponse buildStatsFromAnalysis(Integer totalMutants, Integer totalHumans, BigDecimal ratio);

}
