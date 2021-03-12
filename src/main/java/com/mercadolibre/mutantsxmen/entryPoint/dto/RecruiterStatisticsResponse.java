package com.mercadolibre.mutantsxmen.entryPoint.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Brotherhood of evil mutants recruiter Controller
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "Recruiter Statistics Response", description = "All details about the statistics of the recruitment process.")
public class RecruiterStatisticsResponse {

    /** */
    @JsonProperty("count_mutant_dna")
    private Integer countMutantDna;

    /** */
    @JsonProperty("count_mutant_dna")
    private Integer countHumanDna;

    /** */
    private Double ratio;

}
