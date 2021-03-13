package com.mercadolibre.mutantsxmen.entryPoint.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.mercadolibre.mutantsxmen.core.service.BrotherhoodMutantsService;
import com.mercadolibre.mutantsxmen.core.service.impl.BrotherhoodMutantsServiceImpl;
import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.entryPoint.controller.swagger.BrotherhoodMutantsRecruiterDocumentary;
import com.mercadolibre.mutantsxmen.entryPoint.dto.DetectMutantsRequestDto;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;
import lombok.RequiredArgsConstructor;
import com.aol.cyclops.trycatch.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Brotherhood of evil mutants recruiter Controller
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/brotherhood-recruiter/mutant")
public class BrotherhoodMutantsRecruiterController implements BrotherhoodMutantsRecruiterDocumentary {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(BrotherhoodMutantsServiceImpl.class);

    /** */
    private final BrotherhoodMutantsService brotherhoodMutantsService;

    /**
     * Detect if the DNA Pattern is from a Human or a Mutant
     *
     * @param request the {@linkplain DetectMutantsRequestDto} with the DNA Sequence to analyse
     * @return  OK if the DNA sequence is from a Mutant
     *          Forbidden if the DNA sequence is from a Human
     */
    @PostMapping
    public ResponseEntity<?> detectMutants(@NotNull @Valid @RequestBody final DetectMutantsRequestDto request){

        boolean response = Try.withCatch(() -> brotherhoodMutantsService.isMutant(request.getDna()), DNAValidationException.class).get();

        return ResponseEntity.status(response ? HttpStatus.OK : HttpStatus.FORBIDDEN).build();

    }

    /**
     * Return the statistics of the recruitment of mutants
     *
     * @return the statistics of the recruitment of mutants
     */
    @GetMapping("/stats")
    public ResponseEntity<RecruiterStatisticsResponse> getRecruiterStatistics(){
        return null;
    }

}
