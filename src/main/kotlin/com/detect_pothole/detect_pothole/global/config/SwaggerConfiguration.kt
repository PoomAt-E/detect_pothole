package com.detect_pothole.detect_pothole.global.config

import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    @Bean
    fun getOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("PotHole Detector")
                    .description("PotHole Detector API")
                    .version("v1.0.0")
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("External Description Here")
            )
    }
}
