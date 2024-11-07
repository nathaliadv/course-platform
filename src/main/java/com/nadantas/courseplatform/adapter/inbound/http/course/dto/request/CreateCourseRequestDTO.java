package com.nadantas.courseplatform.adapter.inbound.http.course.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nadantas.courseplatform.shared.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateCourseRequestDTO(
        @NotBlank String name,
        @NotNull @JsonDeserialize(using = Category.CategoryDeserializer.class) Category category,
        boolean active,
        @NotBlank String description
) {
}
