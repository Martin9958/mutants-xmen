package com.mercadolibre.mutantsxmen.entryPoint.controller.swagger;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.mercadolibre.mutantsxmen.core.validator.exception.base.ErrorResponseBody;
import com.mercadolibre.mutantsxmen.entryPoint.dto.DetectMutantsRequestDto;
import com.mercadolibre.mutantsxmen.entryPoint.dto.RecruiterStatisticsResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Brotherhood mutants recruiter swagger documentation
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@Api(value = "Brotherhood Mutants Recruiter Controller", produces = MediaType.APPLICATION_JSON_VALUE)
public interface BrotherhoodMutantsRecruiterDocumentary {

    @ApiOperation("Detect if the DNA Pattern is from a Human or a Mutant")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = HttpStatus.class),
            @ApiResponse(code = 403, message = "Forbidden", response = HttpStatus.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponseBody.class)})
    ResponseEntity<?> detectMutants(@NotNull @Valid @RequestBody final DetectMutantsRequestDto request);

    @ApiOperation("Return the statistics of the recruitment of mutants")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = RecruiterStatisticsResponse.class),
            @ApiResponse(code = 404, message = "NotFound", response = ErrorResponseBody.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorResponseBody.class)})
    ResponseEntity<RecruiterStatisticsResponse> getRecruiterStatistics();

}
