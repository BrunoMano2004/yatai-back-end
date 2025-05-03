package yatai.back.end.yatai_back_end.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI yataiOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Yatai API")
            .description("Documentação completa da API Yatai")
            .version("v1.0.0")
            .contact(new Contact()
                .name("Equipe Yatai")
                .email("contato@yatai.com")
                .url("https://yatai.com"))
            .license(new License().name("Apache 2.0").url("http://springdoc.org")))
        .externalDocs(new ExternalDocumentation()
            .description("Repositório do Projeto")
            .url("https://github.com/yatai/yatai-back-end"));
  }
}