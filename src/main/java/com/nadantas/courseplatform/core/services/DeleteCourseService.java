package com.nadantas.courseplatform.core.services;

import com.nadantas.courseplatform.core.ports.inbound.DeleteCourseInboundPort;
import com.nadantas.courseplatform.core.ports.outbound.PersistenceOutboundPort;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class DeleteCourseService implements DeleteCourseInboundPort {

    PersistenceOutboundPort persistenceOutboundPort;

    @Override
    public void execute(UUID id) {
        persistenceOutboundPort.deleteCourse(id);
    }
}
