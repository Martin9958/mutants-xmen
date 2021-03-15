package com.mercadolibre.mutantsxmen.entryPoint.base;

import com.mercadolibre.mutantsxmen.core.validator.exception.base.CustomResponseEntityExceptionHandler;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

/**
 * Controller Handler exception for integration tests
 *
 * @author Andres Martin Cantor Urrego (martin_990558@hotmail.com)
 * @version 1.0.0
 * @since 08/03/21
 */
public abstract class ControllerHandlerExceptionTest {

    /**
     * Get custom response entity exception handler
     *
     * @return {@link ExceptionHandlerExceptionResolver}
     */
    protected ExceptionHandlerExceptionResolver withExceptionControllerAdvice() {

        final ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new ExceptionHandlerExceptionResolver();

        //here we need to setup a dummy application context that only registers the CustomResponseEntityExceptionHandler
        final StaticApplicationContext applicationContext = new StaticApplicationContext();
        final RootBeanDefinition beanDefinition = new RootBeanDefinition(
                CustomResponseEntityExceptionHandler.class, (String) null, null);

        applicationContext.registerBeanDefinition("advice", beanDefinition);

        //set the application context of the resolver to the dummy application context we just created
        exceptionHandlerExceptionResolver.setApplicationContext(applicationContext);
        exceptionHandlerExceptionResolver.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        //needed in order to force the exception resolver to update it's internal caches
        exceptionHandlerExceptionResolver.afterPropertiesSet();

        return exceptionHandlerExceptionResolver;
    }

}
