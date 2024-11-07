package com.nadantas.courseplatform.adapter.outbound.persistence.repositories;

import com.nadantas.courseplatform.adapter.outbound.persistence.entities.Course;
import com.nadantas.courseplatform.shared.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {

    List<Course> findByNameContainingIgnoreCase(String name);

    List<Course> findByCategory(Category category);

    List<Course> findByNameContainingIgnoreCaseAndCategory(String name, Category category);

}
