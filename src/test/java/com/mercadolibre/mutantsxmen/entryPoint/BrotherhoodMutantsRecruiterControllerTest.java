package com.mercadolibre.mutantsxmen.entryPoint;

import static com.mercadolibre.mutantsxmen.core.validator.util.ValidationMessageField.DNA_REQUEST_MESSAGE;
import static com.mercadolibre.mutantsxmen.utils.FakeObjectCreator.getFakeRecruiterStatisticsResponse;
import static com.mercadolibre.mutantsxmen.utils.Parser.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.mercadolibre.mutantsxmen.StarterIntegrationTest;
import com.mercadolibre.mutantsxmen.core.service.BrotherhoodMutantsService;
import com.mercadolibre.mutantsxmen.core.validator.exception.DNAValidationException;
import com.mercadolibre.mutantsxmen.core.validator.exception.base.ExceptionTypesEnum;
import com.mercadolibre.mutantsxmen.entryPoint.base.ControllerHandlerExceptionTest;
import com.mercadolibre.mutantsxmen.entryPoint.controller.BrotherhoodMutantsRecruiterController;
import com.mercadolibre.mutantsxmen.entryPoint.dto.DetectMutantsRequestDto;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Test the BrotherHood Mutants Recruiter Controller
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {StarterIntegrationTest.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BrotherhoodMutantsRecruiterControllerTest extends ControllerHandlerExceptionTest {

    /** Recruiter Mutants Detect Mutants path */
    private final String PATH = "/v1.0/brotherhood-recruiter/mutant";

    /** Recruiter Mutants stats path */
    private final String STATS_PATH = "/v1.0/brotherhood-recruiter/mutant/stats";

    /** The instance of mock mvc */
    @Autowired
    private MockMvc mockMvc;

    /** The instance {@link WebApplicationContext} */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /** Mock the Brotherhood mutants service*/
    @MockBean
    private BrotherhoodMutantsService service;

    /** Controller under test */
    @InjectMocks
    private BrotherhoodMutantsRecruiterController controller;

    /**
     * This method will run before each {@link org.junit.jupiter.api.Test} annotated method.
     */
    @Before
    public void setUp() {

        controller = new BrotherhoodMutantsRecruiterController(service);

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setHandlerExceptionResolvers(withExceptionControllerAdvice())
                .build();
    }

    @Test
    @DisplayName("detect Mutants. When The DNA Received Is Mutant. Should Return 200 OK")
    public void detectMutants_WhenTheDNAReceivedIsMutant_ShouldReturn200OK() throws Exception {

        //****| Initial parameters |*****/
        final List<String> DNA_MUTANT = Arrays.asList("ATCG","ATCG","ATCG","ATCG");
        final DetectMutantsRequestDto requestDto = new DetectMutantsRequestDto();
        requestDto.setDna(DNA_MUTANT);

        //****| Arrange |*****/
        doReturn(true).when(service).isMutant(any());

        //****| Act |*****/
        final ResultActions result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto)));

        result.andExpect(status().isOk());

    }

    @Test
    @DisplayName("detect Mutants. When The DNA Received Is Not Mutant. Should Return 403 Forbidden")
    public void detectMutants_WhenTheDNAReceivedIsNotMutant_ShouldReturn403Forbidden() throws Exception {

        //****| Initial parameters |*****/
        final List<String> DNA_MUTANT = Arrays.asList("ATCG","GCTA","ATCG","GCTA");
        final DetectMutantsRequestDto requestDto = new DetectMutantsRequestDto();
        requestDto.setDna(DNA_MUTANT);

        //****| Arrange |*****/
        doReturn(false).when(service).isMutant(any());

        //****| Act |*****/
        final ResultActions result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto)));

        result.andExpect(status().isForbidden());

    }

    @Test
    @DisplayName("detect Mutants. When The DNA Received Present Errors. Should Return Bad Request")
    public void detectMutants_WhenTheDNAReceivedPresentErrors_ShouldReturnBadRequest() throws Exception {

        //****| Initial parameters |*****/
        final List<String> DNA_MUTANT = Arrays.asList("ATC","GCTA","ATCG","GCTA");
        final DetectMutantsRequestDto requestDto = new DetectMutantsRequestDto();
        requestDto.setDna(DNA_MUTANT);

        //****| Arrange |*****/
        doThrow(new DNAValidationException(ExceptionTypesEnum.DNA_VALIDATION_ERROR,
                HttpStatus.BAD_REQUEST,
                DNA_REQUEST_MESSAGE)).when(service).isMutant(any());

        //****| Act |*****/
        final ResultActions result = mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(requestDto)));

        //****| Assert |*****/
        result.andExpect(status().isBadRequest());

    }

    @Test
    @DisplayName("get Recruiter Statistics. When Execute The Stats Processing. Should Return 200 OK")
    public void getRecruiterStatistics_WhenExecuteTheStatsProcessing_ShouldReturn200OK() throws Exception {

        //****| Initial parameters |*****/
        final RecruiterStatisticsResponse response = getFakeRecruiterStatisticsResponse();

        //****| Arrange |*****/
        doReturn(response).when(service).getRecruiterMutantStatics();

        //****| Act |*****/
        final ResultActions result = mockMvc.perform(get(STATS_PATH));

        //****| Assert |*****/
        result.andExpect(status().isOk());
        result.andExpect(content().json("{\n" +
                "    \"count_mutant_dna\": 40,\n" +
                "    \"count_human_dna\": 100,\n" +
                "    \"ratio\": 0.4\n" +
                "}"));

    }

}
