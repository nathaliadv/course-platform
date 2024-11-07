package com.nadantas.courseplatform.adapter.inbound.http.course.dto.request;

import lombok.Builder;

@Builder
public record ToggleActiveRequest(
        boolean active
) {
}
