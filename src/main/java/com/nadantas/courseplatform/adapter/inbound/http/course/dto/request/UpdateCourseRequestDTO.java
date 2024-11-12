package com.nadantas.courseplatform.adapter.inbound.http.course.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nadantas.courseplatform.shared.enums.Category;
import jakarta.annotation.Nullable;
import lombok.Builder;

@Builder
public record UpdateCourseRequestDTO(
        @Nullable String name,
        @Nullable @JsonDeserialize(using = Category.CategoryDeserializer.class) Category category,
        @Nullable Boolean active,
        @Nullable String description
) {
}
