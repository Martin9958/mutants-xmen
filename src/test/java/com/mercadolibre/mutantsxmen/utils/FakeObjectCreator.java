package com.mercadolibre.mutantsxmen.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAType;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;
import com.vladmihalcea.hibernate.type.array.internal.ArrayUtil;

/**
 * Fake Object Creator Util
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public class FakeObjectCreator {

    /**
     * Create the Fake Recruiter Statistics Response
     *
     * @return the Fake Recruiter Statistics Response
     */
    public static RecruiterStatisticsResponse  getFakeRecruiterStatisticsResponse(){

        RecruiterStatisticsResponse recruiterStatisticsResponse = new RecruiterStatisticsResponse();
        recruiterStatisticsResponse.setCountMutantDna(40);
        recruiterStatisticsResponse.setCountHumanDna(100);
        recruiterStatisticsResponse.setRatio(BigDecimal.valueOf(0.4));

        return recruiterStatisticsResponse;

    }

    /**
     * Create the Fake DNA Model Saved
     *
     * @param dnaCode the Fake DNA Code
     * @param dnaType the Fake DNA Type
     * @return the Fake DNA Model Saved
     */
    public static DNAModel getFakeDNAModelSaved(String dnaCode, DNAType dnaType){

        DNAModel dnaModelSaved = new DNAModel();
        dnaModelSaved.setId("692873a6-8528-11eb-8dcd-0242ac130003");
        dnaModelSaved.setDna(dnaCode);
        dnaModelSaved.setDnaType(dnaType);
        dnaModelSaved.setCreatedAt(new Date());
        dnaModelSaved.setCreatedBy("TEST MAGENTO");
        dnaModelSaved.setUpdatedAt(new Date());
        dnaModelSaved.setUpdatedBy("TEST MAGNETO");

        return dnaModelSaved;

    }

    /**
     * Create the Fake DNA Matrix
     *
     * @param dna the Fake DNA Code
     * @return the Fake DNA Matrix
     */
    public static ArrayList<ArrayList<String>> getFakeDNAMatrix(List<String> dna){

        ArrayList<ArrayList<String>> dnaMatrix = new ArrayList<>();
        dna.forEach(sequence -> dnaMatrix.add(new ArrayList<>(ArrayUtil.asList(sequence.split("")))));
        return dnaMatrix;

    }

}
