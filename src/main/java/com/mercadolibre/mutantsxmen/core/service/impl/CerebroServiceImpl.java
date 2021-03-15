package com.mercadolibre.mutantsxmen.core.service.impl;

import java.util.Optional;

import com.mercadolibre.mutantsxmen.core.service.CerebroService;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAType;
import com.mercadolibre.mutantsxmen.dataProvider.repository.DNARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Cerebro Service: Offered to expose the operations that Cerebro can do
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@Service
@RequiredArgsConstructor
public class CerebroServiceImpl implements CerebroService {

    /** Repository: used to manipulate the date registered in the database*/
   private final DNARepository repository;

    /** {@inheritDoc} */
    @Override
    public void saveDNARegistry(DNAModel dnaModel) {
        repository.save(dnaModel);
    }

    /** {@inheritDoc} */
    @Override
    public Optional<DNAModel> retrieveRegistryByDNACode(String dnaCode) {
        return repository.findByDna(dnaCode);
    }

    /** {@inheritDoc} */
    @Override
    public Integer retrieveTotalOfMutants() {
        return repository.findTotalDnaByType(DNAType.MUTANT.getType());
    }

    /** {@inheritDoc} */
    @Override
    public Integer retrieveTotalOfHumans() {
        return repository.findTotalDnaByType(DNAType.HUMAN.getType());
    }

}
