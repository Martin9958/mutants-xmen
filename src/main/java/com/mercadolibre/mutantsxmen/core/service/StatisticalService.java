package com.mercadolibre.mutantsxmen.core.service;

import java.math.BigDecimal;

/**
 *
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface StatisticalService {

    /**
     *
     * @param totalMutants
     * @param totalHumans
     * @return
     */
    BigDecimal calculateRatio(Integer totalMutants, Integer totalHumans);

}
