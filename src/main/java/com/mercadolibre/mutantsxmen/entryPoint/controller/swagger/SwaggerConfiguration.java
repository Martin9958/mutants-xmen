package com.mercadolibre.mutantsxmen.entryPoint.controller.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration to show the user the different EndPoints to use in Push Payments module
 *
 * @author Andres Martin Cantor Urrego (andres.cantor@payulatam.com)
 * @author Alejandro Cadena (alejandro.cadena@payulatam.com)
 * @version 1.0.0
 * @since 25/06/20
 */
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    /** Swagger massive-cancellation api version */
    private static final String SWAGGER_API_VERSION = "1.0.0";

    /** The title of the service in Swagger */
    private static final String TITLE = "Brotherhood Of Mutants API";

    /** Description of the Service */
    private static final String DESCRIPTION = "REST full API for Brotherhood Of Mutants Recruiter";

    /**
     * This method returns an instance of ApiSelectorBuilder, which provides a way
     * to control the endpoints exposed by Swagger for version 1.0
     *
     * @return an instance of ApiSelectorBuilder
     */
    @Bean
    public Docket apiV10() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-v1.0")
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex(".*/v1.0/.*"))
                .build();
    }

    /**
     * The Api Info Builder into the swagger
     *
     * @return the Api info with the massive cancellation information
     */
    private ApiInfo apiInfo(){

        return new ApiInfoBuilder()
                .title(TITLE)
                .description(DESCRIPTION)
                .version(SWAGGER_API_VERSION)
                .build();
    }

}
