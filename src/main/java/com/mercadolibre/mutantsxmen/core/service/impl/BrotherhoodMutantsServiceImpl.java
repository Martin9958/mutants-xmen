package com.mercadolibre.mutantsxmen.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.builder.DNABuilder;
import com.mercadolibre.mutantsxmen.core.service.BrotherhoodMutantsService;
import com.mercadolibre.mutantsxmen.core.validator.BrotherhoodRecruiterValidator;
import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Brotherhood of evil mutants recruiter Controller
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@Service
@RequiredArgsConstructor
public class BrotherhoodMutantsServiceImpl implements BrotherhoodMutantsService {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(BrotherhoodMutantsServiceImpl.class);

    /** */
    private final BrotherhoodRecruiterValidator brotherhoodRecruiterValidator;

    /** */
    private final DNABuilder dnaBuilder;


    /** {@inheritDoc} */
    @Override
    public boolean isMutant(List<String> dna) throws DNAValidationException {

        return brotherhoodRecruiterValidator.detectGenX(dnaBuilder.buildDNAMatrix(dna));

    }

    /** {@inheritDoc} */
    @Override
    public RecruiterStatisticsResponse getRecruiterMutantStatics() {
        return null;
    }

}
