package com.nadantas.courseplatform.adapter.outbound.persistence;

import com.nadantas.courseplatform.adapter.outbound.persistence.converter.PersistenceConverter;
import com.nadantas.courseplatform.adapter.outbound.persistence.entities.Course;
import com.nadantas.courseplatform.adapter.outbound.persistence.repositories.CourseRepository;
import com.nadantas.courseplatform.core.model.CourseModel;
import com.nadantas.courseplatform.core.ports.outbound.PersistenceOutboundPort;
import com.nadantas.courseplatform.shared.enums.Category;
import com.nadantas.courseplatform.shared.exceptions.CourseNotFoundException;
import jakarta.persistence.PersistenceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class PersistenceAdapter implements PersistenceOutboundPort {

    private static final Logger logger = LoggerFactory.getLogger(PersistenceAdapter.class);

    private final CourseRepository courseRepository;
    private final PersistenceConverter persistenceConverter;

    @Override
    public UUID saveCourse(CourseModel courseModel) {
        try {
            Course courseSaved = courseRepository.save(this.persistenceConverter.toCourseEntity(courseModel));
            return courseSaved.getId();
        } catch (Exception e) {
            logger.error("Error saving course: {}", courseModel, e);
            throw new PersistenceException("Failed to save course", e);
        }
    }

    @Override
    public List<CourseModel> findAllCourses() {
        List<Course> courseList = courseRepository.findAll();
        return courseList.stream().map( course -> this.persistenceConverter.toCourseModel(course)).toList();
    }

    @Override
    public List<CourseModel> findCoursesByName(String name) {
        List<Course> courseList = courseRepository.findByNameContainingIgnoreCase(name);
        return courseList.stream().map( course -> this.persistenceConverter.toCourseModel(course)).toList();
    }

    @Override
    public List<CourseModel> findCoursesByCategory(Category category) {
        List<Course> courseList = courseRepository.findByCategory(category);
        return courseList.stream().map( course -> this.persistenceConverter.toCourseModel(course)).toList();
    }

    @Override
    public List<CourseModel> findCoursesByNameAndCategory(String name, Category category) {
        List<Course> courseList = courseRepository.findByNameContainingIgnoreCaseAndCategory(name, category);
        return courseList.stream().map( course -> this.persistenceConverter.toCourseModel(course)).toList();
    }

    @Override
    public CourseModel updateCourse(UUID id, CourseModel courseModel) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));

        if (courseModel.name() != null) existingCourse.setName(courseModel.name());
        if (courseModel.category() != null) existingCourse.setCategory(courseModel.category());
        if (courseModel.active() != null) existingCourse.setActive(courseModel.active());
        if (courseModel.description() != null) existingCourse.setDescription(courseModel.description());

        Course courseUpdated = courseRepository.save(existingCourse);
        return this.persistenceConverter.toCourseModel(courseUpdated);
    }

    @Override
    public CourseModel updateActive(UUID id, boolean active) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));

        existingCourse.setActive(active);

        Course courseUpdated = courseRepository.save(existingCourse);
        return this.persistenceConverter.toCourseModel(courseUpdated);
    }

    @Override
    public void deleteCourse(UUID id) {
        courseRepository.deleteById(id);
    }
}
