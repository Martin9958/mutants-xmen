package com.mercadolibre.mutantsxmen.core.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.mercadolibre.mutantsxmen.core.builder.CerebroDNABuilder;
import com.mercadolibre.mutantsxmen.core.builder.RecruitmentStatisticsBuilder;
import com.mercadolibre.mutantsxmen.core.service.BrotherhoodMutantsService;
import com.mercadolibre.mutantsxmen.core.service.CerebroService;
import com.mercadolibre.mutantsxmen.core.service.StatisticalService;
import com.mercadolibre.mutantsxmen.core.validator.BrotherhoodRecruiterValidator;
import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAType;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
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
    private final CerebroDNABuilder cerebroDnaBuilder;

    /** */
    private final RecruitmentStatisticsBuilder statisticsBuilder;

    /** */
    private final CerebroService cerebroService;

    /** */
    private final StatisticalService statisticalService;

    /** */
    private final BrotherhoodRecruiterValidator brotherhoodRecruiterValidator;


    /** {@inheritDoc} */
    @Override
    public boolean isMutant(List<String> dna) throws DNAValidationException {

        LOGGER.info("|****** MUTANT VALIDATION FROM BROTHERHOOD RECRUITER EXECUTED ******|");

        boolean response;
        String dnaCode = cerebroDnaBuilder.buildDNACode(dna);
        Optional<DNAModel> foundDNAModel =  cerebroService.retrieveRegistryByDNACode(dnaCode);

        if(foundDNAModel.isPresent()){
            response = foundDNAModel.get().getDnaType().equals(DNAType.MUTANT);
        }else{
            response = brotherhoodRecruiterValidator.detectGenX(cerebroDnaBuilder.buildDNAMatrix(dna));
            cerebroService.saveDNARegistry(cerebroDnaBuilder.buildDNAModelRegistry(dnaCode,response));
        }

        LOGGER.info("Result DNA : {} for Mutant ", response);
        LOGGER.info("|****** MUTANT VALIDATION FROM BROTHERHOOD RECRUITER FINISH ******|");

        return response;

    }

    /** {@inheritDoc} */
    @Override
    public RecruiterStatisticsResponse getRecruiterMutantStatics() {

        LOGGER.info("|****** RECRUITER STATICS PROCESS EXECUTED ******|");

        Integer totalOfMutants = cerebroService.retrieveTotalOfMutants();
        Integer totalOfHumans = cerebroService.retrieveTotalOfHumans();
        BigDecimal ratio = statisticalService.calculateRatio(totalOfMutants,totalOfHumans);

        LOGGER.info("Result Stats : Mutants -> {} , Humans -> {}, Ratio -> {}", totalOfMutants, totalOfHumans, ratio);

        return statisticsBuilder.buildStatsFromAnalysis(totalOfMutants,totalOfHumans,ratio);

    }

}
