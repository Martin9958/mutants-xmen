package com.mercadolibre.mutantsxmen.core.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.aol.cyclops.trycatch.Try;
import com.mercadolibre.mutantsxmen.core.builder.DNABuilder;
import com.mercadolibre.mutantsxmen.core.service.BrotherhoodMutantsService;
import com.mercadolibre.mutantsxmen.core.service.DNAInformationService;
import com.mercadolibre.mutantsxmen.core.validator.BrotherhoodRecruiterValidator;
import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.core.validator.exception.base.ExceptionTypesEnum;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAType;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

    /** */
    private final DNAInformationService dnaInformationService;


    /** {@inheritDoc} */
    @Override
    public boolean isMutant(List<String> dna) throws DNAValidationException {

        LOGGER.info("|****** MUTANT VALIDATION FROM BROTHERHOOD RECRUITER BEGIN ******|");
        boolean response;
        String dnaCode = dna.stream().map(String::valueOf).collect(Collectors.joining());
        LOGGER.info("DNA CODE {} ", dnaCode);
        Optional<DNAModel> foundDNAModel =  dnaInformationService.retrieveRegistryByDNACode(dnaCode);
        if(foundDNAModel.isPresent()){
            response = foundDNAModel.get().getDnaType().equals(DNAType.MUTANT);
        }else{
            response = brotherhoodRecruiterValidator.detectGenX(dnaBuilder.buildDNAMatrix(dna));
            DNAModel toSave = new DNAModel();
            toSave.setDna(dnaCode);
            toSave.setDnaType(response ? DNAType.MUTANT : DNAType.HUMAN);
            toSave.setCreatedAt(new Date());
            toSave.setCreatedBy("MAGNETO");
            toSave.setUpdatedAt(new Date());
            toSave.setUpdatedBy("MAGNETO");
            dnaInformationService.saveDNARegistry(toSave);
        }
        LOGGER.info("Result DNA : {} for Mutant ", response);
        LOGGER.info("|****** MUTANT VALIDATION FROM BROTHERHOOD RECRUITER FINISH ******|");
        return response;

    }

    /** {@inheritDoc} */
    @Override
    public RecruiterStatisticsResponse getRecruiterMutantStatics() {

        Integer totalOfMutants = dnaInformationService.retrieveTotalOfMutants();
        Integer totalOfHumans = dnaInformationService.retrieveTotalOfHumans();
        BigDecimal ratio = totalOfHumans.equals(0)
                ? BigDecimal.ZERO
                : BigDecimal.valueOf(totalOfMutants).divide(BigDecimal.valueOf(totalOfHumans), 5, RoundingMode.HALF_UP);

        RecruiterStatisticsResponse recruiterStatisticsResponse = new RecruiterStatisticsResponse();
        recruiterStatisticsResponse.setCountMutantDna(totalOfMutants);
        recruiterStatisticsResponse.setCountHumanDna(totalOfHumans);
        recruiterStatisticsResponse.setRatio(ratio);

        return recruiterStatisticsResponse;
    }

}
