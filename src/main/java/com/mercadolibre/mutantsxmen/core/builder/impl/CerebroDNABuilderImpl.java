package com.mercadolibre.mutantsxmen.core.builder.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.mercadolibre.mutantsxmen.core.builder.CerebroDNABuilder;
import com.mercadolibre.mutantsxmen.core.validator.CerebroValidator;
import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAType;
import com.vladmihalcea.hibernate.type.array.internal.ArrayUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@Component
@RequiredArgsConstructor
public class CerebroDNABuilderImpl implements CerebroDNABuilder {

    /** */
    private final CerebroValidator CerebroValidator;

    /** {@inheritDoc} */
    @Override
    public String buildDNACode(List<String> dna) {

        return dna.stream().map(String::valueOf).collect(Collectors.joining());

    }

    /** {@inheritDoc} */
    @Override
    public ArrayList<ArrayList<String>> buildDNAMatrix(List<String> dna) throws DNAValidationException {

        ArrayList<ArrayList<String>> dnaMatrix = new ArrayList<>();

        CerebroValidator.validateDNACodeRequest(dna);
        dna.forEach(sequence -> dnaMatrix.add(new ArrayList<>(ArrayUtil.asList(sequence.toUpperCase().split("")))));
        CerebroValidator.validateDNACodeMatrix(dnaMatrix);

        return dnaMatrix;

    }

    /** {@inheritDoc} */
    @Override
    public DNAModel buildDNAModelRegistry(String dnaCode, boolean isMutant) {

        DNAModel toSave = new DNAModel();

        toSave.setDna(dnaCode);
        toSave.setDnaType( isMutant ? DNAType.MUTANT : DNAType.HUMAN);
        toSave.setCreatedAt(new Date());
        toSave.setCreatedBy("Recruiter Max Eisenhardt - Magneto");
        toSave.setUpdatedAt(new Date());
        toSave.setUpdatedBy("Recruiter Max Eisenhardt - Magneto");

        return toSave;

    }

}
