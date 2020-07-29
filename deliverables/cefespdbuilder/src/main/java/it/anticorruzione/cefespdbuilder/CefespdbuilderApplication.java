/**
 * CEF European single procurement document builder
 */
package it.anticorruzione.cefespdbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@PropertySource("classpath:cefespdbuilder.properties")
@SpringBootApplication
public class CefespdbuilderApplication {
	public static void main(String[] args) {
		SpringApplication.run(CefespdbuilderApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(this.getClass().getPackage().getName())).build();
	}
}