package com.mercadolibre.mutantsxmen.core.builder;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;

/**
 * Cerebro DNA Builder: Used to build the DNA objects necessary for Cerebro processing
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface CerebroDNABuilder {

    /**
     * Build DNA Code in String format from dna
     *
     * @param dna The DNA received from the recruiter
     * @return the DNA mapped into a DNA Code in string format
     */
    String buildDNACode(List<String> dna);

    /**
     * Build The DNA Matrix used in the process of validation in the nitrogen bases
     *
     * @param dna The List received from the recruiter
     * @return The DNA Matrix built
     * @throws DNAValidationException if DNA and DNA Sequence and matrix validations are wrong
     */
    ArrayList<ArrayList<String>> buildDNAMatrix(List<String> dna) throws DNAValidationException;

    /**
     * Build the DNA Model Registry to save in the Database
     *
     * @param dnaCode the DNA code unique in the registries
     * @param isMutant the validation resulting from the Person's genome
     * @return the DNA Model Registry to save in the Database
     */
    DNAModel buildDNAModelRegistry(String dnaCode, boolean isMutant);

}
