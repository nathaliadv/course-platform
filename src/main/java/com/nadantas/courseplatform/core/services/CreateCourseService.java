package com.nadantas.courseplatform.core.services;

import com.nadantas.courseplatform.core.model.CourseModel;
import com.nadantas.courseplatform.core.ports.inbound.CreateCourseInboundPort;
import com.nadantas.courseplatform.core.ports.outbound.PersistenceOutboundPort;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class CreateCourseService implements CreateCourseInboundPort {

    PersistenceOutboundPort persistenceOutboundPort;

    @Override
    public UUID execute(CourseModel courseModel) {
        return persistenceOutboundPort.saveCourse(courseModel);
    }
}
