package com.nadantas.courseplatform.core.ports.inbound;

import com.nadantas.courseplatform.core.model.CourseModel;

import java.util.UUID;

public interface UpdateCourseInboundPort {

    CourseModel execute(UUID id, CourseModel courseModel);

    CourseModel executeToToggleActive(UUID id, boolean active);
}
