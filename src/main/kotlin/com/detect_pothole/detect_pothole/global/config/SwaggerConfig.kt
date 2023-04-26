package com.detect_pothole.detect_pothole.global.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {
    @Bean
    fun api(): OpenAPI {
        val info = Info()
            .title("Detect Pothole")
            .description("Detect Pothole API")
            .version("v0.0.1")

        return OpenAPI().info(info)
    }
}
