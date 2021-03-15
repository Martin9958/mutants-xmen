package com.mercadolibre.mutantsxmen.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Integration test Controller util to parse petition
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 25/06/20
 */
public class Parser {

    /**
     * This utility is used to transform json as String
     *
     * @param obj the object to process
     * @return the string mapped
     */
    public static String asJsonString(final Object obj) {

        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
