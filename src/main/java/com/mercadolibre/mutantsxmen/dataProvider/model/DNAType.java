package com.mercadolibre.mutantsxmen.dataProvider.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * The DNA Type used in the DNA Model Registries after validation
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@Getter
@ToString
@AllArgsConstructor
public enum DNAType {

    MUTANT("MUTANT"),
    HUMAN("HUMAN");

    /** The String value */
    private final String type;

}
