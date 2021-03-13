package com.mercadolibre.mutantsxmen.dataProvider.repository;

import java.util.Optional;

import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DNARepository extends CrudRepository<DNAModel, String> {

    /**
     *
     * @param dnaCode
     * @return
     */
    Optional<DNAModel> findByDna(String dnaCode);

    /**
     *
     * @param dnaType
     * @return
     */
    @Query(value = "select coalesce ((select count(*) from brotherhood_of_mutants.dna " +
            "where type = ?1 ), 0)", nativeQuery = true)
    Integer findTotalDnaByType(DNAType dnaType);

}
