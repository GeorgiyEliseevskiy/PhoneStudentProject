package com.example.phonestudentproject;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Phone control system",
                description = "Engagement with phone system", version = "1.0.0",
                contact = @Contact(
                        name = "Georgiy Eliseevskiy",
                        email = "geliseevskiy@list.ru"
                )
        )
)
public class OpenApiConfig {
}
