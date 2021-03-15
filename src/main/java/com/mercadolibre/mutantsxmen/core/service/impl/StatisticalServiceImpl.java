package com.mercadolibre.mutantsxmen.core.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.mercadolibre.mutantsxmen.core.service.StatisticalService;
import org.springframework.stereotype.Service;

/**
 * Statistical Service: exposed to calculate the different statistical variables for the population analysis
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@Service
public class StatisticalServiceImpl implements StatisticalService {

    /** {@inheritDoc} */
    @Override
    public BigDecimal calculateRatio(Integer totalMutants, Integer totalHumans) {

        return totalHumans.equals(0)
                ? BigDecimal.ZERO : BigDecimal.valueOf(totalMutants)
                .divide(BigDecimal.valueOf(totalHumans), 5, RoundingMode.HALF_UP);

    }

}
