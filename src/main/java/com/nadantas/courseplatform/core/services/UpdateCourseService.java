package com.nadantas.courseplatform.core.services;

import com.nadantas.courseplatform.core.model.CourseModel;
import com.nadantas.courseplatform.core.ports.inbound.UpdateCourseInboundPort;
import com.nadantas.courseplatform.core.ports.outbound.PersistenceOutboundPort;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class UpdateCourseService implements UpdateCourseInboundPort {

    PersistenceOutboundPort persistenceOutboundPort;

    @Override
    public CourseModel execute(UUID id, CourseModel courseModel) {
        return persistenceOutboundPort.updateCourse(id, courseModel);
    }

    @Override
    public CourseModel executeToToggleActive(UUID id, boolean active) {
        return persistenceOutboundPort.updateActive(id, active);
    }
}
