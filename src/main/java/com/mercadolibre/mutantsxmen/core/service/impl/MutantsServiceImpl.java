package com.mercadolibre.mutantsxmen.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.service.MutantsService;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;
import com.vladmihalcea.hibernate.type.array.internal.ArrayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MutantsServiceImpl implements MutantsService {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(MutantsServiceImpl.class);

    @Override
    public boolean isMutant(List<String> dna) {

        ArrayList<ArrayList<String>> dnaMatrix = new ArrayList<>();
        dna.forEach(sequence -> dnaMatrix.add(new ArrayList<>(ArrayUtil.asList(sequence.split("")))));

        LOGGER.info("{}", dna);
        LOGGER.info("{}",dnaMatrix);
        return false;
    }

    @Override
    public RecruiterStatisticsResponse getRecruiterMutantStatics() {
        return null;
    }
}
