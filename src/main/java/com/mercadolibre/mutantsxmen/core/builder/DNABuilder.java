package com.mercadolibre.mutantsxmen.core.builder;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;

public interface DNABuilder {

    ArrayList<ArrayList<String>> buildDNAMatrix(List<String> dna) throws DNAValidationException;

    void buildDNAModelRegistry();

}
