package com.mercadolibre.mutantsxmen.core.service;

import java.math.BigDecimal;

/**
 * Statistical Service: exposed to calculate the different statistical variables for the population analysis
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface StatisticalService {

    /**
     * This method calculate the ratio (Proportion of mutants over the number of humans )
     *
     * @param totalMutants The Number of the Registered mutants registered
     * @param totalHumans The Number of the Registered humans registered
     * @return the calculated ratio
     */
    BigDecimal calculateRatio(Integer totalMutants, Integer totalHumans);

}
