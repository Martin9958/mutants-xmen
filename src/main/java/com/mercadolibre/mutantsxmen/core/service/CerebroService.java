package com.mercadolibre.mutantsxmen.core.service;

import java.util.Optional;

import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;

/**
 * Cerebro Service: Offered to expose the operations that Cerebro can do
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface CerebroService {

    /**
     * This method save The DNA Registry previous validated in the Database
     *
     * @param dnaModel the DNA Model to save
     */
    void saveDNARegistry(DNAModel dnaModel);

    /**
     * This method retrieve the Registry from the database by the DNA code sequence
     *
     * @param dnaCode the DNA Code in String Format to found in the database registries
     * @return the Registry from the database if exist else return an Empty DNA model
     */
    Optional<DNAModel> retrieveRegistryByDNACode(String dnaCode);

    /**
     * This method retrieve the total of mutants registered
     *
     * @return  the total of mutants registered
     */
    Integer retrieveTotalOfMutants();

    /**
     * This method retrieve the total of humans registered
     *
     * @return the total of humans registered
     */
    Integer retrieveTotalOfHumans();

}
