package com.mercadolibre.mutantsxmen.entryPoint.controller;

import com.mercadolibre.mutantsxmen.entryPoint.controller.swagger.BrotherhoodMutantsRecruiterDocumentary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/v1.0/brotherhood-recruiter/mutant")
public class BrotherhoodMutantsRecruiterController implements BrotherhoodMutantsRecruiterDocumentary {

    /**
     * Detect if the DNA Pattern is from a Human or a Mutant
     *
     * @return
     */
    @PostMapping
    public ResponseEntity<String> detectMutants(){
        return null;
    }

    /**
     * Return the statistics of the recruitment of mutants
     *
     * @return the statistics of the recruitment of mutants
     */
    @GetMapping("/stats")
    public ResponseEntity<String> getRecruiterStatistics(){
        return null;
    }

}
