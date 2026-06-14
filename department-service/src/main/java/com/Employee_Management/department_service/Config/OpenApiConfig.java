package com.Employee_Management.department_service.Config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Department Service APIs",
                version = "1.0",
                description = "Department Management APIs",
                contact = @Contact(
                        name = "Achint Kumar"
                )
        )
)
public class OpenApiConfig {
}
