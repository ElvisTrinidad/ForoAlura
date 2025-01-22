package com.Foro.Alura.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    //metodo sacado de springdoc.org
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",  //es la llave, tiene q ser el mismo que vas a usar en los controller
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Voll.med API")
                        .description("API Rest de la aplicación foro_alura, que contiene las funcionalidades CRUD de Profesores y Alumnos")
                        .contact(new Contact()
                                .name("Alumno Backend")
                                .email("backend@voll.med"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://voll.med/api/licencia")));
    }
}
