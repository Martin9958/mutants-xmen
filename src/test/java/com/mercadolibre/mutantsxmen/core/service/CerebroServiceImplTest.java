package com.mercadolibre.mutantsxmen.core.service;

import static com.mercadolibre.mutantsxmen.utils.FakeObjectCreator.getFakeDNAModelSaved;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.mercadolibre.mutantsxmen.core.service.impl.CerebroServiceImpl;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAModel;
import com.mercadolibre.mutantsxmen.dataProvider.model.DNAType;
import com.mercadolibre.mutantsxmen.dataProvider.repository.DNARepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test the Cerebro Service Implementation
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@ExtendWith(MockitoExtension.class)
public class CerebroServiceImplTest {

    /** Mock Repository */
    @Mock
    private DNARepository repository;

    /** Object under test */
    @InjectMocks
    private CerebroServiceImpl cerebroService;

    @Test
    @DisplayName("save DNA Registry. When A DNA Model to Save is Received. Should Execute the repository save method")
    void saveDNARegistry_WhenADNAModelToSaveIsReceived_ShouldExecuteTheRepositorySaveMethod(){

        //*****| Initial Parameters |*****/
        final String EXPECTED_DNA_STRING = "ATCGATCGATCGATCG";
        final DNAModel DNA_TO_SAVE = getFakeDNAModelSaved(EXPECTED_DNA_STRING, DNAType.MUTANT);

        //*****| Act |*****/
        cerebroService.saveDNARegistry(DNA_TO_SAVE);

        //*****| Assert |*****/
        verify(repository, times(1)).save(DNA_TO_SAVE);

    }

    @Test
    @DisplayName("save DNA Registry. When An Exists DNA Code is Received. Should Return A Valid DNA Model")
    void retrieveRegistryByDNACode_WhenAnExistsDNACodeIsReceived_ShouldReturnAValidDNAModel(){

        //*****| Initial Parameters |*****/
        final String EXPECTED_DNA_STRING = "ATCGGCTAATCGGCTA";
        final DNAModel EXPECTED_DNA_MODEL = getFakeDNAModelSaved(EXPECTED_DNA_STRING,DNAType.HUMAN);

        //*****| Arrange |*****/
        when(repository.findByDna(EXPECTED_DNA_STRING)).thenReturn(Optional.of(EXPECTED_DNA_MODEL));

        //*****| Act |*****/
        Optional<DNAModel> response = cerebroService.retrieveRegistryByDNACode(EXPECTED_DNA_STRING);

        //*****| Assert |*****/
        assertThat(response).isPresent();

    }

    @Test
    @DisplayName("save DNA Registry. When A DNA Code Not Registered Is Received. Should Return An Empty DNA Model")
    void retrieveRegistryByDNACode_W(){

        //*****| Initial Parameters |*****/
        final String EXPECTED_DNA_STRING = "CTCGGCTAATCGGCTC";

        //*****| Arrange |*****/
        when(repository.findByDna(EXPECTED_DNA_STRING)).thenReturn(Optional.empty());

        //*****| Act |*****/
        Optional<DNAModel> response = cerebroService.retrieveRegistryByDNACode(EXPECTED_DNA_STRING);

        //*****| Assert |*****/
        assertThat(response).isNotPresent();

    }

    @Test
    @DisplayName("save DNA Registry. When Exists Multiple Mutants In The Data Base Registries. Should Return total of mutants greater than Zero")
    void retrieveTotalOfMutants_WhenExistsMultipleMutantsInTheDataBaseRegistries_ShouldReturnTotalOfMutantsGreaterThanZero(){

        //*****| Arrange |*****/
        when(repository.findTotalDnaByType(DNAType.MUTANT.getType())).thenReturn(100);

        //*****| Act |*****/
        Integer response = cerebroService.retrieveTotalOfMutants();

        //*****| Assert |*****/
        assertThat(response).isNotZero();

    }

    @Test
    @DisplayName("save DNA Registry. When Not Exists Multiple Mutants In The Data Base Registries. Should Return Total Of Mutants Equal Than Zero")
    void retrieveTotalOfMutants_WhenNotExistsMultipleMutantsInTheDataBaseRegistries_ShouldReturnTotalOfMutantsEqualThanZero(){

        //*****| Arrange |*****/
        when(repository.findTotalDnaByType(DNAType.MUTANT.getType())).thenReturn(0);

        //*****| Act |*****/
        Integer response = cerebroService.retrieveTotalOfMutants();

        //*****| Assert |*****/
        assertThat(response).isZero();

    }

    @Test
    @DisplayName("save DNA Registry. When Exists Multiple Human In The Data Base Registries. Should Return Total Of Humans Greater Than Zero")
    void retrieveTotalOfHumans_WhenExistsMultipleHumanInTheDataBaseRegistries_ShouldReturnTotalOfHumansGreaterThanZero(){

        //*****| Arrange |*****/
        when(repository.findTotalDnaByType(DNAType.HUMAN.getType())).thenReturn(400);

        //*****| Act |*****/
        Integer response = cerebroService.retrieveTotalOfHumans();

        //*****| Assert |*****/
        assertThat(response).isNotZero();

    }

    @Test
    @DisplayName("save DNA Registry. When Not Exists Multiple Humans In The Data Base Registries. Should Return Total Of Humans Equal Than Zero")
    void retrieveTotalOfHumans_WhenNotExistsMultipleHumansInTheDataBaseRegistries_ShouldReturnTotalOfHumansEqualThanZero(){

        //*****| Arrange |*****/
        when(repository.findTotalDnaByType(DNAType.HUMAN.getType())).thenReturn(0);

        //*****| Act |*****/
        Integer response = cerebroService.retrieveTotalOfHumans();

        //*****| Assert |*****/
        assertThat(response).isZero();

    }

}
