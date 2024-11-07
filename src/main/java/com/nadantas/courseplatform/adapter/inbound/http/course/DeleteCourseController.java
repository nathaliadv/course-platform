package com.nadantas.courseplatform.adapter.inbound.http.course;

import com.nadantas.courseplatform.core.ports.inbound.DeleteCourseInboundPort;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
public class DeleteCourseController {

    private DeleteCourseInboundPort deleteCourseInboundPort;

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> execute(@PathVariable UUID id) {
        try {
            deleteCourseInboundPort.execute(id);
            return ResponseEntity.ok("Course " + id + " deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
