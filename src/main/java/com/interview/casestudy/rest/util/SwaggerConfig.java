package com.interview.casestudy.rest.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Class used to build swagger documentation 
 * @author roksolana antonyuk
 *
 */
@Configuration
@Import({SpringDataRestConfiguration.class, BeanValidatorPluginsConfiguration.class})
public class SwaggerConfig {
   @Bean
   public Docket apiDocket() {
       
       Docket docket =  new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.interview.casestudy.rest"))
                .paths(PathSelectors.any())
                .build();
       
       return docket;
    } 
}