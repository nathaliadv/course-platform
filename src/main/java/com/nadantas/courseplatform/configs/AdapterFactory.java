package com.nadantas.courseplatform.configs;

import com.nadantas.courseplatform.adapter.outbound.persistence.converter.PersistenceConverter;
import com.nadantas.courseplatform.adapter.outbound.persistence.repositories.CourseRepository;
import com.nadantas.courseplatform.adapter.outbound.persistence.PersistenceAdapter;
import com.nadantas.courseplatform.core.ports.outbound.PersistenceOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterFactory {

    @Autowired
    CourseRepository courseRepository;
    @Autowired
    PersistenceConverter persistenceConverter;

    @Bean
    public PersistenceOutboundPort persistenceAdapter(CourseRepository courseRepository, PersistenceConverter persistenceConverter) {
        return new PersistenceAdapter(courseRepository, persistenceConverter);
    }
}
