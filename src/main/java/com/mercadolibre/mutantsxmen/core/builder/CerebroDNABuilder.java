package com.mercadolibre.mutantsxmen.core.builder;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;

/**
 *
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface CerebroDNABuilder {

    /**
     *
     * @param dna
     * @return
     */
    String buildDNACode(List<String> dna);

    /**
     *
     * @param dna
     * @return
     * @throws DNAValidationException
     */
    ArrayList<ArrayList<String>> buildDNAMatrix(List<String> dna) throws DNAValidationException;

    /**
     *
     * @param dnaCode
     * @param isMutant
     * @return
     */
    DNAModel buildDNAModelRegistry(String dnaCode, boolean isMutant);

}
