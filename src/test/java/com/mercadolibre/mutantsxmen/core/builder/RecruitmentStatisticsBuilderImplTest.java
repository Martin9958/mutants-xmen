package com.mercadolibre.mutantsxmen.core.builder;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import com.mercadolibre.mutantsxmen.core.builder.impl.RecruitmentStatisticsBuilderImpl;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test the Recruitment Statistics Builder Implementation
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public class RecruitmentStatisticsBuilderImplTest {

    /** Object under Test */
    private RecruitmentStatisticsBuilder recruitmentStatisticsBuilder;

    /** This method will run before each {@link Test} annotated method. */
    @BeforeEach
    void setUp() {
        recruitmentStatisticsBuilder = new RecruitmentStatisticsBuilderImpl();
    }

    @Test
    @DisplayName("build Stats From Analysis. When The Values Are Received. Should Return The Recruiter Statistics Response")
    void buildStatsFromAnalysis_WhenTheValuesAreReceived_ShouldReturnTheRecruiterStatisticsResponse() {

        //*****| Initial parameters |*****/
        final Integer TOTAL_MUTANTS = 40;
        final Integer TOTAL_HUMANS = 100;
        final BigDecimal RATIO = BigDecimal.valueOf(0.4);

        //*****| Act |*****/
        RecruiterStatisticsResponse response = recruitmentStatisticsBuilder.buildStatsFromAnalysis(TOTAL_MUTANTS, TOTAL_HUMANS, RATIO);

        //*****| Assert |*****/
        assertThat(response).isNotNull();
        assertThat(response.getCountMutantDna()).isEqualTo(TOTAL_MUTANTS);
        assertThat(response.getCountHumanDna()).isEqualTo(TOTAL_HUMANS);
        assertThat(response.getRatio()).isEqualTo(RATIO);

    }

    /**
     * This method will run after each {@link Test} annotated method.
     */
    @AfterEach
    void tearDown() {
        recruitmentStatisticsBuilder = null;
    }

}
