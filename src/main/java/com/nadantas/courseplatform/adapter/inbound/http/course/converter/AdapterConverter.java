package com.nadantas.courseplatform.adapter.inbound.http.course.converter;

import com.nadantas.courseplatform.adapter.inbound.http.course.dto.request.UpdateCourseRequestDTO;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.response.CourseResponseDTO;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.request.CreateCourseRequestDTO;
import com.nadantas.courseplatform.core.model.CourseModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdapterConverter {

    public CourseModel convertToCourseModel(CreateCourseRequestDTO requestDto) {
        return CourseModel.builder()
                .name(requestDto.name())
                .category(requestDto.category())
                .description(requestDto.description())
                .active(requestDto.active())
                .build();
    }

    public CourseModel convertToCourseModel(UpdateCourseRequestDTO requestDto) {
        return CourseModel.builder()
                .name(requestDto.name())
                .category(requestDto.category())
                .description(requestDto.description())
                .active(requestDto.active())
                .build();
    }

    public List<CourseResponseDTO> toResponseDTOList(List<CourseModel> courseModelList) {
        return courseModelList.stream()
                .map(courseModel -> toResponseDTO(courseModel))
                .collect(Collectors.toList());
    }

    public CourseResponseDTO toResponseDTO(CourseModel courseModel) {
        return CourseResponseDTO.builder()
                .id(courseModel.id())
                .name(courseModel.name())
                .category(courseModel.category())
                .description(courseModel.description())
                .active(courseModel.active())
                .createdAt(courseModel.createdAt())
                .updatedAt(courseModel.updatedAt())
                .build();
    }
}
