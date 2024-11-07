package com.nadantas.courseplatform.adapter.outbound.persistence.converter;

import com.nadantas.courseplatform.adapter.outbound.persistence.entities.Course;
import com.nadantas.courseplatform.core.model.CourseModel;
import org.springframework.stereotype.Component;

@Component
public class PersistenceConverter {

    public Course toCourseEntity(CourseModel courseModel) {
        return Course.builder()
                .name(courseModel.name())
                .category(courseModel.category())
                .description(courseModel.description())
                .active(courseModel.active())
                .build();
    }

    public CourseModel toCourseModel(Course course) {
        return CourseModel.builder()
                .id(course.getId())
                .name(course.getName())
                .category(course.getCategory())
                .description(course.getDescription())
                .active(course.isActive())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }
}
