package com.mercadolibre.mutantsxmen.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.mercadolibre.mutantsxmen.core.service.impl.StatisticalServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test the Cerebro Service Implementation
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public class StatisticalServiceImplTest {

    /** Object under Test */
    private StatisticalService statisticalService;

    /** This method will run before each {@link Test} annotated method. */
    @BeforeEach
    void setUp() {
        statisticalService = new StatisticalServiceImpl();
    }

    @Test
    @DisplayName("calculate Ratio. When The Total Of Humans Different to Zero. Should Return The Correct Calculated Ratio")
    void calculateRatio_WhenTheTotalOfHumansDifferentToZero_ShouldReturnTheCorrectCalculatedRatio() {

        //*****| Initial parameters |*****/
        final Integer TOTAL_MUTANTS = 40;
        final Integer TOTAL_HUMANS = 1250;

        //*****| Act |*****/
        BigDecimal response = statisticalService.calculateRatio(TOTAL_MUTANTS, TOTAL_HUMANS);

        //*****| Assert |*****/
        assertThat(response).isNotZero().isEqualTo(BigDecimal.valueOf(0.032).setScale(5, RoundingMode.HALF_UP));

    }

    @Test
    @DisplayName("calculate Ratio. When The Total Of Humans is equals to Zero. Should Return The Calculated Ratio With Zero Value")
    void calculateRatio_WhenTheTotalOfHumansEqualsToZero_ShouldReturnTheCalculatedRatioWithZeroValue() {

        //*****| Initial parameters |*****/
        final Integer TOTAL_MUTANTS = 50;
        final Integer TOTAL_HUMANS = 0;

        //*****| Act |*****/
        BigDecimal response = statisticalService.calculateRatio(TOTAL_MUTANTS, TOTAL_HUMANS);

        //*****| Assert |*****/
        assertThat(response).isZero();

    }

    /**
     * This method will run after each {@link Test} annotated method.
     */
    @AfterEach
    void tearDown() {
        statisticalService = null;
    }

}
