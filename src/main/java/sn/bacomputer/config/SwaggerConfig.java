package sn.bacomputer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger configuration for API documentation.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("API for Product")
                        .description("REST API for managing Product resources")
                        .version("1.0")
                        .license(new License().name("MIT"))
                        .contact(new Contact().email("contact@example.com")));
    }
}
