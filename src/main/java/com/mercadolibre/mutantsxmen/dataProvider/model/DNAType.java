package com.mercadolibre.mutantsxmen.dataProvider.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum DNAType {

    MUTANT("MUTANT"),
    HUMAN("HUMAN");

    private final String type;

}
