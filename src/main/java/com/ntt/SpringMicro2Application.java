package com.ntt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
public class SpringMicro2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicro2Application.class, args);
	}
	
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("NTTData")
                .description("NTTData prueba API reference for developers, teh error handler is implement using \n"
                        + " The IETF devised RFC 7807 effor, which creates a generalized error-handling schema.\n"
                        + "https://tools.ietf.org/html/rfc7807")
                .termsOfServiceUrl("http://www.linkedin.com/in/cberm3o")
                .contact(new Contact("C. Bermeo", "", "google.com"))
                .license("cberm3o License")
                .licenseUrl("google.com")
                .version("1.0")
                .build();
	} 

	@Bean
	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("prueba-spis")
				.apiInfo(apiInfo())
				//set up JWT input
				//.securityContexts(Arrays.asList(securityContext()))
				//.securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ntt"))
				.paths(PathSelectors.any())
				.build()
				.tags(new Tag("PRUEBA API", "All apis relating to billing service") {},
						new Tag("cuentas", "Make cuenta"));
	}

}
