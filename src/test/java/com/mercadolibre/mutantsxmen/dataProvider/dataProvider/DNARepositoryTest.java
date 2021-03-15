package com.mercadolibre.mutantsxmen.dataProvider.dataProvider;

import static com.mercadolibre.mutantsxmen.utils.FakeObjectCreator.getFakeDNAModelSaved;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import java.util.Optional;

import com.mercadolibre.mutantsxmen.StarterIntegrationTest;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAType;
import com.mercadolibre.mutantsxmen.dataProvider.repository.DNARepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Test the DNA Repository
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StarterIntegrationTest.class})
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
@SpringBootTest
public class DNARepositoryTest {

    /** Object under Test */
    @Autowired
    private DNARepository repository;

    @Test
    @DisplayName("find By Dna. When The DNA Was Registered. Should Return The DNA Model")
    public void findByDna_WhenTheDNAWasRegistered_ShouldReturnTheDNAModel() {

        //*****| Initial parameters |*****/
        final String DNA_HUMAN_REQUEST = "ATGCGACAGTGCTTATTTAGACGGGCGTCATCACTG";
        final DNAModel DNA_TO_SAVE = getFakeDNAModelSaved(DNA_HUMAN_REQUEST, DNAType.MUTANT);

        //*****| Initial Arrange |*****/
        final DNAModel saved = repository.save(DNA_TO_SAVE);

        //*****| Act |*****/
        Optional<DNAModel> response = repository.findByDna(DNA_HUMAN_REQUEST);

        //*****| Assert |*****/
        assertThat(response).isPresent();

        //*****| Initial Arrange |*****/
        repository.delete(saved);

    }

    @Test
    @DisplayName("find By Dna. When The DNA Was Not Registered. Should Return The Optional Empty")
    public void findByDna_WhenTheDNAWasNotRegistered_ShouldReturnTheOptionalEmpty() {

        //*****| Initial parameters |*****/
        final String DNA_HUMAN_REQUEST = "ATCGATCGATCGATCG";

        //*****| Act |*****/
        Optional<DNAModel> response = repository.findByDna(DNA_HUMAN_REQUEST);

        //*****| Assert |*****/
        assertThat(response).isNotPresent();

    }

    @Test
    @DisplayName("find Total Dna By Type. When Exists The DNAs With The Received Type. Should Return Greater Than Zero")
    public void findTotalDnaByType_WhenExistsTheDNAsWithTheReceivedType_ShouldReturnGreaterThanZero() {

        //*****| Initial parameters |*****/
        final String DNA_HUMAN_REQUEST = "ATTATATATTAATTGATACGCCCCG";
        final DNAModel DNA_TO_SAVE = getFakeDNAModelSaved(DNA_HUMAN_REQUEST, DNAType.MUTANT);

        //*****| Initial Arrange |*****/
        final DNAModel saved = repository.save(DNA_TO_SAVE);

        //*****| Act |*****/
        Integer response = repository.findTotalDnaByType(DNAType.MUTANT.getType());

        //*****| Assert |*****/
        assertThat(response).isNotZero().isEqualTo(1);

        //*****| Initial Arrange |*****/
        repository.delete(saved);

    }

    @Test
    @DisplayName("find Total Dna By Type. When Not Exists The DNAs With The Received Type. Should Return Zero")
    public void findTotalDnaByType_WhenNotExistsTheDNAsWithTheReceivedType_ShouldReturnZero() {

        //*****| Act |*****/
        Integer response = repository.findTotalDnaByType(DNAType.HUMAN.getType());

        //*****| Assert |*****/
        assertThat(response).isZero();

    }

}
