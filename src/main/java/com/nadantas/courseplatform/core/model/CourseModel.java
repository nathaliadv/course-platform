package com.nadantas.courseplatform.core.model;

import com.nadantas.courseplatform.shared.enums.Category;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CourseModel(
        UUID id,
        String name,
        Category category,
        Boolean active,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
