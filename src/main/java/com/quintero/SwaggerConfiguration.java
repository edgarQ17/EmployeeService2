package com.quintero;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@EnableSwagger2
@Configuration
public class SwaggerConfiguration {

    public  static final Contact SUPPORTED_CONTACTS = new Contact("Edgar Q","http://youwebsiteEdgarQ.com","support@mail.com");



    @Bean
    public Docket newAPi(){
        Set producers = new HashSet<>(Arrays.asList("application/json","application/xml"));
        Set consumers = new HashSet<>(Arrays.asList("application/json","application/xml"));

        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .produces(producers)
                .consumes(consumers);
    }

    private ApiInfo apiInfo() {
        return  new ApiInfoBuilder().title("Employee Service")
                .description("Employee ServiceWith Swagger Documentation")
                .termsOfServiceUrl("http://youwebsiteEdgarQ.com")
                .contact(SUPPORTED_CONTACTS)
                .license("Employee licence 1.0")
                .licenseUrl("http://youwebsiteEdgarQ.com")
                .version("3.0")
                .build();  ///this builds the apiInfo that shows on Swaggger
    }
}
