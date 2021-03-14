package com.mercadolibre.mutantsxmen.core.builder.impl;

import java.math.BigDecimal;

import com.mercadolibre.mutantsxmen.core.builder.RecruitmentStatisticsBuilder;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@Component
public class RecruitmentStatisticsBuilderImpl implements RecruitmentStatisticsBuilder {

    /** */
    @Override
    public RecruiterStatisticsResponse buildStatsFromAnalysis(Integer totalMutants, Integer totalHumans, BigDecimal ratio) {

        RecruiterStatisticsResponse recruiterStatisticsResponse = new RecruiterStatisticsResponse();
        recruiterStatisticsResponse.setCountMutantDna(totalMutants);
        recruiterStatisticsResponse.setCountHumanDna(totalHumans);
        recruiterStatisticsResponse.setRatio(ratio);

        return recruiterStatisticsResponse;
    }

}
