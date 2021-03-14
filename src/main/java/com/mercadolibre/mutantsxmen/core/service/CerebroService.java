package com.mercadolibre.mutantsxmen.core.service;

import java.util.Optional;

import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;

/**
 *
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface CerebroService {

    /**
     *
     * @param dnaModel
     */
    void saveDNARegistry(DNAModel dnaModel);

    /**
     *
     * @param dnaCode
     * @return
     */
    Optional<DNAModel> retrieveRegistryByDNACode(String dnaCode);

    /**
     *
     * @return
     */
    Integer retrieveTotalOfMutants();

    /**
     *
     * @return
     */
    Integer retrieveTotalOfHumans();

}
