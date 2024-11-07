package com.nadantas.courseplatform.adapter.inbound.http.course.dto.response;

import com.nadantas.courseplatform.shared.enums.Category;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CourseResponseDTO(
        UUID id,
        String name,
        Category category,
        boolean active,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
