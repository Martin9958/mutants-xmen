package com.mercadolibre.mutantsxmen.core.validator;

import static com.mercadolibre.mutantsxmen.utils.FakeObjectCreator.getFakeDNAMatrix;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.impl.BrotherHoodRecruiterValidatorImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Test the Brotherhood Recruiter Validator Implementation
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public class BrotherhoodRecruiterValidatorImplTest {

    /** Object under Test */
    private BrotherhoodRecruiterValidator brotherhoodRecruiterValidator;

    /** This method will run before each {@link Test} annotated method. */
    @BeforeEach
    void setUp() {
        brotherhoodRecruiterValidator = new BrotherHoodRecruiterValidatorImpl();
    }

    @Test
    @DisplayName("detect Gen X. When DNA Matrix Received Contains More Than 2 Repetitions. Should Return True for Mutant")
    void detectGenX_WhenDNAMatrixReceivedContainsMoreThanTwoRepetitions_ShouldReturnTrueForMutant() {

        //*****| Initial parameters |*****/
        final List<String> DNA_MUTANT_REQUEST = Arrays.asList("ATGCGG","CAGTGC","TTAGGT","AGGAGG","CCCCTA","TCACTG");
        final ArrayList<ArrayList<String>> EXPECTED_DNA_MATRIX = getFakeDNAMatrix(DNA_MUTANT_REQUEST);

        //*****| Act |*****/
        boolean response = brotherhoodRecruiterValidator.detectGenX(EXPECTED_DNA_MATRIX);

        //*****| Arrange |*****/
        assertTrue(response);

    }

    @Test
    @DisplayName("detect Gen X. When DNA Matrix Received Contains 1 Repetition. Should Return False for Mutant")
    void detectGenX_WhenDNAMatrixReceivedContainsOneRepetition_ShouldReturnTrueForMutant() {

        //*****| Initial parameters |*****/
        final List<String> DNA_HUMAN_ONE_REQUEST = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGAAGG","GCGTCA","TCACTG");
        final ArrayList<ArrayList<String>> EXPECTED_DNA_MATRIX = getFakeDNAMatrix(DNA_HUMAN_ONE_REQUEST);

        //*****| Act |*****/
        boolean response = brotherhoodRecruiterValidator.detectGenX(EXPECTED_DNA_MATRIX);

        //*****| Arrange |*****/
        assertFalse(response);

    }

    @Test
    @DisplayName("detect Gen X. When DNA Matrix Received Not Contains Repetitions. Should Return False for Mutant")
    void detectGenX_WhenDNAMatrixReceivedNotContainsRepetitions_ShouldReturnTrueForMutant() {

        //*****| Initial parameters |*****/
        final List<String> DNA_HUMAN_REQUEST = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG");
        final ArrayList<ArrayList<String>> EXPECTED_DNA_MATRIX = getFakeDNAMatrix(DNA_HUMAN_REQUEST);

        //*****| Act |*****/
        boolean response = brotherhoodRecruiterValidator.detectGenX(EXPECTED_DNA_MATRIX);

        //*****| Arrange |*****/
        assertFalse(response);

    }

    /**
     * This method will run after each {@link Test} annotated method.
     */
    @AfterEach
    void tearDown() {
        brotherhoodRecruiterValidator = null;
    }

}
