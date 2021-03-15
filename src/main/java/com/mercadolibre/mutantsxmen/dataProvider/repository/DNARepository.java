package com.mercadolibre.mutantsxmen.dataProvider.repository;

import java.util.Optional;

import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Cerebro Validator: exposed to validate the Received DNA Code and it's variations
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public interface DNARepository extends CrudRepository<DNAModel, String> {

    /**
     * This method find the DNA Registry by the DNA Code
     *
     * @param dnaCode The DNA Code in String format used to realize the searching process
     * @return if the DNA code exist return the DNA Model otherwise an empty DNA Model
     */
    Optional<DNAModel> findByDna(String dnaCode);

    /**
     * This method Fin the Total of Persons According to the DNA Type Present
     *
     * @param dnaType The DNA type to search (MUTANT or HUMAN)
     * @return the Total of Persons According to the DNA Type Present
     */
    @Query(value = "select coalesce ((select count(*) from brotherhood_of_mutants.dna " +
            "where dna_type = ?1 ), 0)", nativeQuery = true)
    Integer findTotalDnaByType(String dnaType);

}
