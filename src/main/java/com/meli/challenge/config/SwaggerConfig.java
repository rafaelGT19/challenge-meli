package com.meli.challenge.config;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuration for swagger doc plugin
 *
 * @author <a href="rasgut19@gmail.com">Rafael Gutierrez</a>
 * @version 1.0
 * @since 1.0
 */
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.meli.challenge"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo(
			"Coupon processor aPI",
			"Provide logic to process favorite items coupon",
			"v1.0",
			null,
			new Contact("Rafael Gutierrez", "", "rasgut19@gmail.com"),
			null,
			null,
			new ArrayList<>());
	}

}
