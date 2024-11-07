package com.nadantas.courseplatform.adapter.inbound.http.course;

import com.nadantas.courseplatform.adapter.inbound.http.course.converter.AdapterConverter;
import com.nadantas.courseplatform.core.model.CourseModel;
import com.nadantas.courseplatform.core.ports.inbound.GetCourseInboundPort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class GetCourseController {

    private final GetCourseInboundPort getCourseInboundPort;
    private final AdapterConverter converter;

    @GetMapping("/all")
    public ResponseEntity<Object> execute() {
        try {
            List<CourseModel> listCourseResponseDTO = getCourseInboundPort.execute();
            return ResponseEntity.ok(converter.toResponseDTOList(listCourseResponseDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all/filter")
    public ResponseEntity<Object> executeByFilter(@RequestParam(required = false) String name,
                                                  @RequestParam(required = false) String category) {
        try {
            List<CourseModel> listCourseResponseDTO;
            if (name != null && !name.isEmpty() && category != null && !category.isEmpty()) {
                listCourseResponseDTO = getCourseInboundPort.executeByNameAndCategory(name, category);
            } else if (name != null && !name.isEmpty()) {
                listCourseResponseDTO = getCourseInboundPort.executeByName(name);
            } else if (category != null && !category.isEmpty()) {
                listCourseResponseDTO = getCourseInboundPort.executeByCategory(category);
            } else {
                listCourseResponseDTO = getCourseInboundPort.execute();
            }
            return ResponseEntity.ok(converter.toResponseDTOList(listCourseResponseDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
