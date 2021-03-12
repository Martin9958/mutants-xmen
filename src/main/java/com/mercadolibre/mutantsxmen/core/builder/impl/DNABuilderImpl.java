package com.mercadolibre.mutantsxmen.core.builder.impl;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.builder.DNABuilder;
import com.mercadolibre.mutantsxmen.core.validator.DNAPetitionValidator;
import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.vladmihalcea.hibernate.type.array.internal.ArrayUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DNABuilderImpl implements DNABuilder {

    /** */
    private final DNAPetitionValidator DNAPetitionValidator;

    @Override
    public ArrayList<ArrayList<String>> buildDNAMatrix(List<String> dna) throws DNAValidationException {

        ArrayList<ArrayList<String>> dnaMatrix = new ArrayList<>();

        DNAPetitionValidator.validateDNACodeRequest(dna);
        dna.forEach(sequence -> dnaMatrix.add(new ArrayList<>(ArrayUtil.asList(sequence.toUpperCase().split("")))));
        DNAPetitionValidator.validateDNACodeMatrix(dnaMatrix);

        return dnaMatrix;

    }

    @Override
    public void buildDNAModelRegistry() {

    }

}
