package com.nadantas.courseplatform.adapter.inbound.http.course;

import com.nadantas.courseplatform.adapter.inbound.http.course.converter.AdapterConverter;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.request.ToggleActiveRequest;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.request.UpdateCourseRequestDTO;
import com.nadantas.courseplatform.core.model.CourseModel;
import com.nadantas.courseplatform.core.ports.inbound.UpdateCourseInboundPort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class UpdateCourseController {

    private final UpdateCourseInboundPort updateCourseInboundPort;
    private final AdapterConverter converter;

    @PutMapping("/{id}")
    public ResponseEntity<Object> execute(@PathVariable UUID id, @RequestBody UpdateCourseRequestDTO updateCourseRequestDTO) {
        try {
            CourseModel courseUpdated = updateCourseInboundPort.execute(id, converter.convertToCourseModel(updateCourseRequestDTO));
            return ResponseEntity.ok(converter.toResponseDTO(courseUpdated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> executeToToggleActiveStatus(@PathVariable UUID id, @RequestBody ToggleActiveRequest toggleActiveRequest) {
        try {
            CourseModel courseUpdated = updateCourseInboundPort.executeToToggleActive(id, toggleActiveRequest.active());
            return ResponseEntity.ok(converter.toResponseDTO(courseUpdated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
