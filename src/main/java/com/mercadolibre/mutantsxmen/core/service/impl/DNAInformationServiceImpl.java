package com.mercadolibre.mutantsxmen.core.service.impl;

import java.util.Optional;

import com.mercadolibre.mutantsxmen.core.service.DNAInformationService;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAType;
import com.mercadolibre.mutantsxmen.dataProvider.repository.DNARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DNAInformationServiceImpl implements DNAInformationService {

    /** */
   private final DNARepository repository;

    /** */
    @Override
    public void saveDNARegistry(DNAModel dnaModel) {
        repository.save(dnaModel);
    }

    /** */
    @Override
    public Optional<DNAModel> retrieveRegistryByDNACode(String dnaCode) {
        return repository.findByDna(dnaCode);
    }

    /** */
    @Override
    public Integer retrieveTotalOfMutants() {
        return repository.findTotalDnaByType(DNAType.MUTANT);
    }

    /** */
    @Override
    public Integer retrieveTotalOfHumans() {
        return repository.findTotalDnaByType(DNAType.HUMAN);
    }

}
