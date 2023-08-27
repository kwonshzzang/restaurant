package kr.co.kwonshzzang.restaurant.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Restaurant API 명세서",
                description = "API 명세서",
                version = "v1",
                contact = @Contact(
                        name = "kwonshzzang",
                        email = "kwonshzzang@gmail.com"
                )
        )
)

@Configuration
public class SwaggerConfig {
}
