package com.mercadolibre.mutantsxmen.entryPoint.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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

    /** The Number of the Registered mutants validated */
    @JsonProperty("count_mutant_dna")
    @ApiModelProperty(position = 1, notes = "count_mutant_dna", example = "40")
    private Integer countMutantDna;

    /** The Number of the Registered humans validated */
    @JsonProperty("count_human_dna")
    @ApiModelProperty(position = 2, notes = "count_human_dna", example = "100")
    private Integer countHumanDna;

    /** The ratio value accourding the number of mutants over the number of humans */
    @JsonProperty("ratio")
    @ApiModelProperty(position = 3, notes = "ratio", example = "0.4")
    private BigDecimal ratio;

}
