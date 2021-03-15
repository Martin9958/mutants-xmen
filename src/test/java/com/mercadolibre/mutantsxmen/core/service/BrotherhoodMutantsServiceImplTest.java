package com.mercadolibre.mutantsxmen.core.service;

import static com.mercadolibre.mutantsxmen.utils.FakeObjectCreator.getFakeDNAMatrix;
import static com.mercadolibre.mutantsxmen.utils.FakeObjectCreator.getFakeDNAModelSaved;
import static com.mercadolibre.mutantsxmen.utils.FakeObjectCreator.getFakeRecruiterStatisticsResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mercadolibre.mutantsxmen.core.builder.CerebroDNABuilder;
import com.mercadolibre.mutantsxmen.core.builder.RecruitmentStatisticsBuilder;
import com.mercadolibre.mutantsxmen.core.service.impl.BrotherhoodMutantsServiceImpl;
import com.mercadolibre.mutantsxmen.core.validator.BrotherhoodRecruiterValidator;
import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAType;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test the Brotherhood Mutants Service Implementation
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@ExtendWith(MockitoExtension.class)
public class BrotherhoodMutantsServiceImplTest {

    /** Cerebro DNA Builder Mock */
    @Mock
    private CerebroDNABuilder cerebroDnaBuilder;

    /** Recruitment Statistics Builder Mock*/
    @Mock
    private RecruitmentStatisticsBuilder statisticsBuilder;

    /** Cerebro Service Mock*/
    @Mock
    private CerebroService cerebroService;

    /** Statistical Service Mock */
    @Mock
    private StatisticalService statisticalService;

    /** Brotherhood Recruiter Validator Mock */
    @Mock
    private BrotherhoodRecruiterValidator brotherhoodRecruiterValidator;

    /** Object under test */
    @InjectMocks
    private BrotherhoodMutantsServiceImpl brotherhoodMutantsService;

    @Test
    @DisplayName("is Mutant. When The Received DNA it Have already been validated. Should Return The result of it Previous validation")
    void isMutant_WhenTheReceivedDNAItHaveAlreadyBeenValidated_ShouldReturnTheResultOfItPreviousValidation() throws DNAValidationException {

        //*****| Initial Parameters |*****/
        final List<String> DNA_SAVED =  Arrays.asList("ATCG","ATCG", "ATCG", "ATCG");
        final String EXPECTED_DNA_STRING = "ATCGATCGATCGATCG";
        final Optional<DNAModel> EXPECTED_FROM_DB = Optional.of(getFakeDNAModelSaved(EXPECTED_DNA_STRING, DNAType.MUTANT));

        //*****| Arrange |*****/
        when(cerebroDnaBuilder.buildDNACode(DNA_SAVED)).thenReturn(EXPECTED_DNA_STRING);
        when(cerebroService.retrieveRegistryByDNACode(EXPECTED_DNA_STRING)).thenReturn(EXPECTED_FROM_DB);

        //*****| Act |*****/
        boolean response = brotherhoodMutantsService.isMutant(DNA_SAVED);

        //*****| Assert |*****/
        assertTrue(response);
        verify(cerebroDnaBuilder, times(0)).buildDNAMatrix(DNA_SAVED);
        verify(cerebroDnaBuilder, times(0)).buildDNAModelRegistry(EXPECTED_DNA_STRING, true);

    }

    @Test
    @DisplayName("is Mutant. When The Received DNA it Haven't already been validated. Should Return The result of it validation")
    void isMutant_WhenTheReceivedDNAItHaveNotAlreadyBeenValidated_ShouldReturnTheResultOfItValidation() throws DNAValidationException {

        //*****| Initial Parameters |*****/
        final List<String> DNA_NOT_SAVED =  Arrays.asList("ATCG","GCTA", "ATCG", "GCTA");
        final String EXPECTED_DNA_STRING = "ATCGGCTAATCGGCTA";
        final ArrayList<ArrayList<String>> DNA_FAKE_MATRIX = getFakeDNAMatrix(DNA_NOT_SAVED);
        final Optional<DNAModel> EXPECTED_FROM_DB = Optional.empty();

        //*****| Arrange |*****/
        when(cerebroDnaBuilder.buildDNACode(DNA_NOT_SAVED)).thenReturn(EXPECTED_DNA_STRING);
        when(cerebroDnaBuilder.buildDNAMatrix(DNA_NOT_SAVED)).thenReturn(DNA_FAKE_MATRIX);
        when(cerebroService.retrieveRegistryByDNACode(EXPECTED_DNA_STRING)).thenReturn(EXPECTED_FROM_DB);
        when(brotherhoodRecruiterValidator.detectGenX(DNA_FAKE_MATRIX)).thenReturn(false);

        //*****| Act |*****/
        boolean response = brotherhoodMutantsService.isMutant(DNA_NOT_SAVED);

        //*****| Assert |*****/
        assertFalse(response);
        verify(cerebroDnaBuilder, times(1)).buildDNAMatrix(DNA_NOT_SAVED);
        verify(cerebroDnaBuilder, times(1)).buildDNAModelRegistry(EXPECTED_DNA_STRING, false);

    }

    @Test
    @DisplayName("is Mutant. When The Received DNA Is From A Mutant. Should Return True")
    void isMutant_WhenTheReceivedDNAIsFromAMutant_ShouldReturnTrue() throws DNAValidationException {

        //*****| Initial Parameters |*****/
        final List<String> DNA_MUTANT =  Arrays.asList("ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG");
        final String EXPECTED_DNA_STRING = "ATGCGACAGTGCTTATGTAGAAGGCCCCTATCACTG";
        final ArrayList<ArrayList<String>> DNA_FAKE_MATRIX = getFakeDNAMatrix(DNA_MUTANT);
        final Optional<DNAModel> EXPECTED_FROM_DB = Optional.empty();
        final DNAModel EXPECTED_DNA_MODEL = getFakeDNAModelSaved(EXPECTED_DNA_STRING, DNAType.MUTANT);

        //*****| Arrange |*****/
        when(cerebroDnaBuilder.buildDNACode(DNA_MUTANT)).thenReturn(EXPECTED_DNA_STRING);
        when(cerebroDnaBuilder.buildDNAMatrix(DNA_MUTANT)).thenReturn(DNA_FAKE_MATRIX);
        when(cerebroService.retrieveRegistryByDNACode(EXPECTED_DNA_STRING)).thenReturn(EXPECTED_FROM_DB);
        when(brotherhoodRecruiterValidator.detectGenX(DNA_FAKE_MATRIX)).thenReturn(true);
        when(cerebroDnaBuilder.buildDNAModelRegistry(EXPECTED_DNA_STRING,true)).thenReturn(EXPECTED_DNA_MODEL);
        doNothing().when(cerebroService).saveDNARegistry(EXPECTED_DNA_MODEL);

        //*****| Act |*****/
        boolean response = brotherhoodMutantsService.isMutant(DNA_MUTANT);

        //*****| Assert |*****/
        assertTrue(response);

    }

    @Test
    @DisplayName("is Mutant. When The Received DNA Is From A Human. Should Return False")
    void isMutant_WhenTheReceivedDNAIsFromAHuman_ShouldReturnFalse() throws DNAValidationException {

        //*****| Initial Parameters |*****/
        final List<String> DNA_HUMAN = Arrays.asList("ATGCGA","CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG");
        final String EXPECTED_DNA_STRING = "ATGCGACAGTGCTTATTTAGACGGGCGTCATCACTG";
        final ArrayList<ArrayList<String>> DNA_FAKE_MATRIX = getFakeDNAMatrix(DNA_HUMAN);
        final Optional<DNAModel> EXPECTED_FROM_DB = Optional.empty();
        final DNAModel EXPECTED_DNA_MODEL = getFakeDNAModelSaved(EXPECTED_DNA_STRING, DNAType.HUMAN);

        //*****| Arrange |*****/
        when(cerebroDnaBuilder.buildDNACode(DNA_HUMAN)).thenReturn(EXPECTED_DNA_STRING);
        when(cerebroDnaBuilder.buildDNAMatrix(DNA_HUMAN)).thenReturn(DNA_FAKE_MATRIX);
        when(cerebroService.retrieveRegistryByDNACode(EXPECTED_DNA_STRING)).thenReturn(EXPECTED_FROM_DB);
        when(brotherhoodRecruiterValidator.detectGenX(DNA_FAKE_MATRIX)).thenReturn(false);
        when(cerebroDnaBuilder.buildDNAModelRegistry(EXPECTED_DNA_STRING,false)).thenReturn(EXPECTED_DNA_MODEL);
        doNothing().when(cerebroService).saveDNARegistry(EXPECTED_DNA_MODEL);

        //*****| Act |*****/
        boolean response = brotherhoodMutantsService.isMutant(DNA_HUMAN);

        //*****| Assert |*****/
        assertFalse(response);

    }

    @Test
    @DisplayName("get Recruiter Mutant Statics. When The Process Is Executed. Should Return The Statics Correctly")
    void getRecruiterMutantStatics_WhenTheProcessIsExecuted_ShouldReturnTheStaticsCorrectly(){

        //*****| Initial Parameters |*****/
        final Integer TOTAL_MUTANT = 40;
        final Integer TOTAL_HUMAN = 100;
        final BigDecimal RATIO = BigDecimal.valueOf(0.4);

        //*****| Arrange |*****/
        when(cerebroService.retrieveTotalOfMutants()).thenReturn(TOTAL_MUTANT);
        when(cerebroService.retrieveTotalOfHumans()).thenReturn(TOTAL_HUMAN);
        when(statisticalService.calculateRatio(TOTAL_MUTANT, TOTAL_HUMAN)).thenReturn(RATIO);
        when(statisticsBuilder.buildStatsFromAnalysis(TOTAL_MUTANT,TOTAL_HUMAN,RATIO))
                .thenReturn(getFakeRecruiterStatisticsResponse());

        //*****| Act |*****/
        RecruiterStatisticsResponse response = brotherhoodMutantsService.getRecruiterMutantStatics();

        //*****| Assert |*****/
        assertThat(response).isNotNull();
        assertThat(response.getCountHumanDna()).isEqualTo(TOTAL_HUMAN);
        assertThat(response.getCountMutantDna()).isEqualTo(TOTAL_MUTANT);
        assertThat(response.getRatio()).isEqualTo(RATIO);

    }

}
