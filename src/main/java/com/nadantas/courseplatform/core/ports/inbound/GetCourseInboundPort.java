package com.nadantas.courseplatform.core.ports.inbound;

import com.nadantas.courseplatform.core.model.CourseModel;

import java.util.List;

public interface GetCourseInboundPort {

    List<CourseModel> execute();

    List<CourseModel> executeByName(String name);

    List<CourseModel> executeByCategory(String category);

    List<CourseModel> executeByNameAndCategory(String name, String category);
}
