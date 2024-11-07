package com.nadantas.courseplatform.adapter.inbound.http.course;

import com.nadantas.courseplatform.adapter.inbound.http.course.converter.AdapterConverter;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.request.CreateCourseRequestDTO;
import com.nadantas.courseplatform.core.ports.inbound.CreateCourseInboundPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class CreateCourseController {

    private final CreateCourseInboundPort createCourseInboundPort;
    private final AdapterConverter converter;

    @PostMapping("/")
    public ResponseEntity<Object> execute(@Valid @RequestBody CreateCourseRequestDTO requestDto) {
        try {
            UUID courseId = createCourseInboundPort.execute(converter.convertToCourseModel(requestDto));
            return ResponseEntity.ok("Course registered successfully! Id: " + courseId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
