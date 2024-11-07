package com.nadantas.courseplatform.core.services;

import com.nadantas.courseplatform.core.model.CourseModel;
import com.nadantas.courseplatform.core.ports.inbound.GetCourseInboundPort;
import com.nadantas.courseplatform.core.ports.outbound.PersistenceOutboundPort;
import com.nadantas.courseplatform.shared.enums.Category;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetCourseService implements GetCourseInboundPort {

    PersistenceOutboundPort persistenceOutboundPort;

    public List<CourseModel> executeByName(String name) {
        return persistenceOutboundPort.findCoursesByName(name);
    }

    public List<CourseModel> execute() {
        return persistenceOutboundPort.findAllCourses();
    }

    @Override
    public List<CourseModel> executeByCategory(String category) {
        return persistenceOutboundPort.findCoursesByCategory(getCategory(category));
    }

    @Override
    public List<CourseModel> executeByNameAndCategory(String name, String category) {
        return persistenceOutboundPort.findCoursesByNameAndCategory(name, getCategory(category));
    }

    private Category getCategory(String category) {
        try {
            return Category.fromType(category);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category type: " + category + ". Possible categories are: " + Category.getPossibleCategories());
        }
    }
}
