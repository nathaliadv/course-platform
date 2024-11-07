package com.nadantas.courseplatform.core.ports.outbound;

import com.nadantas.courseplatform.core.model.CourseModel;
import com.nadantas.courseplatform.shared.enums.Category;

import java.util.List;
import java.util.UUID;

public interface PersistenceOutboundPort {

    UUID saveCourse(CourseModel courseModel);

    List<CourseModel> findAllCourses();

    List<CourseModel> findCoursesByName(String name);

    List<CourseModel> findCoursesByCategory(Category category);

    List<CourseModel> findCoursesByNameAndCategory(String name, Category category);

    CourseModel updateCourse(UUID id, CourseModel courseModel);

    CourseModel updateActive(UUID id, boolean active);

    void deleteCourse(UUID id);
}
