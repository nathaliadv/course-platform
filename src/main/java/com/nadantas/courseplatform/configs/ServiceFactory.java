package com.nadantas.courseplatform.configs;

import com.nadantas.courseplatform.core.ports.inbound.CreateCourseInboundPort;
import com.nadantas.courseplatform.core.ports.inbound.DeleteCourseInboundPort;
import com.nadantas.courseplatform.core.ports.inbound.GetCourseInboundPort;
import com.nadantas.courseplatform.core.ports.inbound.UpdateCourseInboundPort;
import com.nadantas.courseplatform.core.ports.outbound.PersistenceOutboundPort;
import com.nadantas.courseplatform.core.services.CreateCourseService;
import com.nadantas.courseplatform.core.services.DeleteCourseService;
import com.nadantas.courseplatform.core.services.GetCourseService;
import com.nadantas.courseplatform.core.services.UpdateCourseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceFactory {

    @Bean
    public CreateCourseInboundPort createCourseService(PersistenceOutboundPort persistenceOutboundPort) {
        return new CreateCourseService(persistenceOutboundPort);
    }

    @Bean
    public GetCourseInboundPort getCourseService(PersistenceOutboundPort persistenceOutboundPort) {
        return new GetCourseService(persistenceOutboundPort);
    }

    @Bean
    public UpdateCourseInboundPort updateCourseService(PersistenceOutboundPort persistenceOutboundPort) {
        return new UpdateCourseService(persistenceOutboundPort);
    }

    @Bean
    public DeleteCourseInboundPort deleteCourseService(PersistenceOutboundPort persistenceOutboundPort) {
        return new DeleteCourseService(persistenceOutboundPort);
    }
}
