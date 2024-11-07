package com.nadantas.courseplatform.core.ports.inbound;

import java.util.UUID;

public interface DeleteCourseInboundPort {

    void execute(UUID id);
}
