package com.nadantas.courseplatform.core.ports.inbound;

import com.nadantas.courseplatform.core.model.CourseModel;

import java.util.UUID;

public interface CreateCourseInboundPort {

    UUID execute(CourseModel courseModel);
}
