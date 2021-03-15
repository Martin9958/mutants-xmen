package com.mercadolibre.mutantsxmen.core.builder;

import static com.mercadolibre.mutantsxmen.core.validator.util.ValidationMessageField.DNA_REQUEST_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.builder.impl.CerebroDNABuilderImpl;
import com.mercadolibre.mutantsxmen.core.validator.CerebroValidator;
import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.core.validator.exception.base.ExceptionTypesEnum;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAType;
import com.vladmihalcea.hibernate.type.array.internal.ArrayUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

/**
 * Test the Cerebro DNA Builder Implementation
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@ExtendWith(MockitoExtension.class)
public class CerebroDNABuilderImplTest {

    /** Logger */
    private static final Logger LOGGER = LogManager.getLogger(CerebroDNABuilderImplTest.class);

    /** Cerebro validator Mock */
    @Mock
    private CerebroValidator cerebroValidator;

    /** Object Under Test */
    @InjectMocks
    private CerebroDNABuilderImpl cerebroDNABuilder;

    @Test
    @DisplayName("buildDNACode. When A DNA List with upper case is Received. Should Return A String DNA Sequence")
    void buildDNACode_WhenADNAListWithUpperCaseIsReceived_ShouldReturnAStringDNASequence(){

        //*****| Initial parameters |*****/
        final List<String> DNA_REQUEST = Arrays.asList("ATTAT", "ATATT", "AATTG", "ATACG", "CCCCG");

        //*****| Act |*****/
        String response = cerebroDNABuilder.buildDNACode(DNA_REQUEST);

        //*****| Assert |*****/
        assertThat(response).isEqualTo("ATTATATATTAATTGATACGCCCCG");

    }

    @Test
    @DisplayName("buildDNACode. When A DNA List with lower case is Received. Should Return A String DNA Sequence")
    void buildDNACode_WhenADNAListWithLowerCaseIsReceived_ShouldReturnAStringDNASequence(){

        //*****| Initial parameters |*****/
        final List<String> DNA_REQUEST = Arrays.asList("attat", "atatt", "aattg", "aattg", "ccccg");

        //*****| Act |*****/
        String response = cerebroDNABuilder.buildDNACode(DNA_REQUEST);

        //*****| Assert |*****/
        assertThat(response).isEqualTo("ATTATATATTAATTGAATTGCCCCG");

    }

    @Test
    @DisplayName("buildDNAMatrix. When A correct DNA List is Received. Should Return A DNA Correct Matrix")
    void buildDNAMatrix_WhenACorrectDNAListIsReceived_ShouldReturnADNACorrectMatrix() throws DNAValidationException {

        //*****| Initial parameters |*****/
        final List<String> DNA_REQUEST = Arrays.asList("ATGCAT", "AGCATC", "ACGGGA", "ATAAAT", "CCTCCC", "CGGTAT");

        //*****| Arrange |*****/
        doNothing().when(cerebroValidator).validateDNACodeRequest(DNA_REQUEST);

        //*****| Act |*****/
        ArrayList<ArrayList<String>> response = cerebroDNABuilder.buildDNAMatrix(DNA_REQUEST);
        LOGGER.info("{}", response);

        //*****| Assert |*****/
        assertThat(response).isNotEmpty().isNotNull().satisfies(dnaSequence -> assertThat(dnaSequence.size() == DNA_REQUEST.size()));

    }

    @Test
    @DisplayName("buildDNAMatrix. When A Wrong DNA Code is Received. Should Return An Exception")
    void buildDNAMatrix_WhenAWrongDNAListIsReceived_ShouldReturnAnException() throws DNAValidationException {

        //*****| Initial parameters |*****/
        final List<String> DNA_REQUEST = Collections.emptyList();

        //*****| Arrange |*****/
        doThrow(new DNAValidationException(
                ExceptionTypesEnum.DNA_VALIDATION_ERROR,
                HttpStatus.BAD_REQUEST,DNA_REQUEST_MESSAGE))
                .when(cerebroValidator).validateDNACodeRequest(DNA_REQUEST);

        //*****| Act |*****/
        DNAValidationException response = assertThrows(
                DNAValidationException.class,
                () -> cerebroDNABuilder.buildDNAMatrix(DNA_REQUEST));

        //*****| Assert |*****/
        assertTrue(response.getMessage().contains("The DNA Code isn't correct"));

    }

    @Test
    @DisplayName("buildDNAMatrix. When A Wrong DNA Matrix Generated is received. Should Return An Exception")
    void buildDNAMatrix_WhenAWrongDNAMatrixGeneratedIsReceived_ShouldReturnAnException() throws DNAValidationException {

        //*****| Initial parameters |*****/
        final List<String> DNA_REQUEST = Arrays.asList("ATGCA", "AGCATC", "ACGGGA", "ATAAAT", "CCTCCC", "CGGTAT");
        ArrayList<ArrayList<String>> DNA_MATRIX = new ArrayList<>();
        DNA_REQUEST.forEach(sequence -> DNA_MATRIX.add(new ArrayList<>(ArrayUtil.asList(sequence.toUpperCase().split("")))));

        //*****| Arrange |*****/
        doThrow(new DNAValidationException(
                ExceptionTypesEnum.DNA_VALIDATION_ERROR,
                HttpStatus.BAD_REQUEST,DNA_REQUEST_MESSAGE))
                .when(cerebroValidator).validateDNACodeMatrix(DNA_MATRIX);

        //*****| Act |*****/
        DNAValidationException response = assertThrows(
                DNAValidationException.class,
                () -> cerebroDNABuilder.buildDNAMatrix(DNA_REQUEST));

        //*****| Assert |*****/
        assertTrue(response.getMessage().contains("The DNA Code isn't correct"));

    }

    @Test
    @DisplayName("buildDNAModelRegistry. When A Mutant DNA is received. Should Return A Mutant DNA Model")
    void buildDNAModelRegistry_WhenAMutantDNAIsReceived_ShouldAMutantDNAModel(){

        //*****| Initial parameters |*****/
        final String DNA_CODE = "ATCGATCGATCGATCG";
        final boolean IS_MUTANT_RESULT = true;

        //*****| Act |*****/
        DNAModel response = cerebroDNABuilder.buildDNAModelRegistry(DNA_CODE,IS_MUTANT_RESULT);

        //*****| Assert |*****/
        assertThat(response).isNotNull();
        assertThat(response.getDna()).isEqualTo(DNA_CODE);
        assertThat(response.getDnaType()).isEqualTo(DNAType.MUTANT);

    }

    @Test
    @DisplayName("buildDNAModelRegistry. When A Human DNA is received. Should Return A Human DNA Model")
    void buildDNAModelRegistry_WhenAHumanDNAIsReceived_ShouldReturnAHumanDNAModel() {

        //*****| Initial parameters |*****/
        final String DNA_CODE = "ATGCGACAGTGCTTATTTAGACGGGCGTCATCACTG";
        final boolean IS_MUTANT_RESULT = false;

        //*****| Act |*****/
        DNAModel response = cerebroDNABuilder.buildDNAModelRegistry(DNA_CODE,IS_MUTANT_RESULT);

        //*****| Assert |*****/
        assertThat(response).isNotNull();
        assertThat(response.getDna()).isEqualTo(DNA_CODE);
        assertThat(response.getDnaType()).isEqualTo(DNAType.HUMAN);

    }

}
