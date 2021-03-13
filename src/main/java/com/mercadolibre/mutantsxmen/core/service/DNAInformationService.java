package com.mercadolibre.mutantsxmen.core.service;

import java.util.Optional;

import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;

public interface DNAInformationService {

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
