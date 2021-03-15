package com.mercadolibre.mutantsxmen.core.validator;

import static com.mercadolibre.mutantsxmen.core.validator.util.ValidationMessageField.DNA_MATRIX_CORRECT_NITROGEN_BASES_MESSAGE;
import static com.mercadolibre.mutantsxmen.core.validator.util.ValidationMessageField.DNA_MATRIX_NITROGEN_BASES_VALID_NUMBER_MESSAGE;
import static com.mercadolibre.mutantsxmen.core.validator.util.ValidationMessageField.DNA_MATRIX_SQUARE_MESSAGE;
import static com.mercadolibre.mutantsxmen.core.validator.util.ValidationMessageField.DNA_REQUEST_MESSAGE;
import static com.mercadolibre.mutantsxmen.utils.FakeObjectCreator.getFakeDNAMatrix;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.core.validator.impl.CerebroValidatorImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test the Cerebro Validator Implementation
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public class CerebroValidatorImplTest {

    /** Logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(CerebroValidatorImplTest.class);

    /** Object under Test */
    private CerebroValidator cerebroValidator;

    /** This method will run before each {@link Test} annotated method. */
    @BeforeEach
    void setUp() {
        cerebroValidator = new CerebroValidatorImpl();
    }

    @Test
    @DisplayName("validate DNA Code Request. When DNA Received Is Null. Should Throw An Exception")
    void validateDNACodeRequest_WhenDNAReceivedIsNull_ShouldThrowAnException() {

        //*****| Act |*****/
        DNAValidationException response = assertThrows(
                DNAValidationException.class,
                () -> cerebroValidator.validateDNACodeRequest(null));
        LOGGER.info(response.getMessage());

        //*****| Arrange |*****/
        assertNotNull(response);
        assertTrue(response.getMessage().contains(DNA_REQUEST_MESSAGE + null));

    }

    @Test
    @DisplayName("validate DNA Code Request. When DNA Received Contains Null. Should Throw An Exception")
    void validateDNACodeRequest_WhenDNAReceivedContainsNull_ShouldThrowAnException() {

        //*****| Initial parameters |*****/
        final List<String> DNA_REQUEST = new ArrayList<>();
        DNA_REQUEST.add(null);

        //*****| Act |*****/
        DNAValidationException response = assertThrows(
                DNAValidationException.class,
                () -> cerebroValidator.validateDNACodeRequest(DNA_REQUEST));
        LOGGER.info(response.getMessage());

        //*****| Arrange |*****/
        assertNotNull(response);
        assertTrue(response.getMessage().contains( DNA_REQUEST_MESSAGE + "[null]"));

    }

    @Test
    @DisplayName("validate DNA Code Request. When DNA Received Is Empty. Should Throw An Exception")
    void validateDNACodeRequest_WhenDNAReceivedIsEmpty_ShouldThrowAnException() {

        //*****| Initial parameters |*****/
        final List<String> DNA_REQUEST = new ArrayList<>();

        //*****| Act |*****/
        DNAValidationException response = assertThrows(
                DNAValidationException.class,
                () -> cerebroValidator.validateDNACodeRequest(DNA_REQUEST));
        LOGGER.info(response.getMessage());

        //*****| Arrange |*****/
        assertNotNull(response);
        assertTrue(response.getMessage().contains( DNA_REQUEST_MESSAGE + "[]"));

    }

    @Test
    @DisplayName("validate DNA Code Matrix. When DNA Matrix Received Is Not Square. Should Throw An Exception")
    void validateDNACodeMatrix_WhenDNAMatrixReceivedIsNotSquare_ShouldThrowAnException() {

        //*****| Initial parameters |*****/
        final List<String> DNA_REQUEST = Arrays.asList("ATCG", "ATCG", "ATCG", "AAA");
        final ArrayList<ArrayList<String>> EXPECTED_DNA_MATRIX = getFakeDNAMatrix(DNA_REQUEST);

        //*****| Act |*****/
        DNAValidationException response = assertThrows(
                DNAValidationException.class,
                () -> cerebroValidator.validateDNACodeMatrix(EXPECTED_DNA_MATRIX));
        LOGGER.info(response.getMessage());

        //*****| Arrange |*****/
        assertNotNull(response);
        assertTrue(response.getMessage().contains(DNA_MATRIX_SQUARE_MESSAGE));

    }

    @Test
    @DisplayName("validate DNA Code Matrix. When DNA Matrix Size Received Is less than Nitrogen Base Number. Should Throw An Exception")
    void validateDNACodeMatrix_WhenDNAMatrixSizeReceivedIsLessThanNitrogenBaseNumber_ShouldThrowAnException() {

        //*****| Initial parameters |*****/
        final List<String> DNA_REQUEST = Arrays.asList("ATC", "ATC", "ATC");
        final ArrayList<ArrayList<String>> EXPECTED_DNA_MATRIX = getFakeDNAMatrix(DNA_REQUEST);

        //*****| Act |*****/
        DNAValidationException response = assertThrows(
                DNAValidationException.class,
                () -> cerebroValidator.validateDNACodeMatrix(EXPECTED_DNA_MATRIX));
        LOGGER.info(response.getMessage());

        //*****| Arrange |*****/
        assertNotNull(response);
        assertTrue(response.getMessage().contains(DNA_MATRIX_NITROGEN_BASES_VALID_NUMBER_MESSAGE));

    }

    @Test
    @DisplayName("validate DNA Code Matrix. When DNA Matrix Received Contains A Wrong Character. Should Throw An Exception")
    void validateDNACodeMatrix_WhenDNAMatrixReceivedContainsAWrongCharacter_ShouldThrowAnException() {

        //*****| Initial parameters |*****/
        final List<String> DNA_REQUEST = Arrays.asList("ATCG", "ATCG", "ATCG", "AXCT");
        final ArrayList<ArrayList<String>> EXPECTED_DNA_MATRIX = getFakeDNAMatrix(DNA_REQUEST);

        //*****| Act |*****/
        DNAValidationException response = assertThrows(
                DNAValidationException.class,
                () -> cerebroValidator.validateDNACodeMatrix(EXPECTED_DNA_MATRIX));
        LOGGER.info(response.getMessage());

        //*****| Arrange |*****/
        assertNotNull(response);
        assertTrue(response.getMessage().contains(DNA_MATRIX_CORRECT_NITROGEN_BASES_MESSAGE));

    }

    /**
     * This method will run after each {@link Test} annotated method.
     */
    @AfterEach
    void tearDown() {
        cerebroValidator = null;
    }

}
