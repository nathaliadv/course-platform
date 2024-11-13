package com.nadantas.courseplatform;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Course Platform",
                description = "API responsible for course management",
                version = "1"
        )
)
public class CoursePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoursePlatformApplication.class, args);
    }

}
