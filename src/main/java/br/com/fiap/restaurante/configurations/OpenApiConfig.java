package br.com.fiap.restaurante.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("API Restaurante")
                    .description("Documentação da API do sistema de gerenciamento de restaurantes")
                    .version("v1")
                    .contact(new Contact()
                        .name("Equipe de Desenvolvimento")
                        .email("contato@restaurante.com")
                    )
                );
    }
}
