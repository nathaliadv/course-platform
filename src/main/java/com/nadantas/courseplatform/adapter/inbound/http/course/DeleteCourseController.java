package com.nadantas.courseplatform.adapter.inbound.http.course;

import com.nadantas.courseplatform.core.ports.inbound.DeleteCourseInboundPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Courses", description = "Related to deleting an existing course.")
public class DeleteCourseController {

    private DeleteCourseInboundPort deleteCourseInboundPort;

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a course by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course deleted successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, deletion failed",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Object> execute(@PathVariable UUID id) {
        try {
            deleteCourseInboundPort.execute(id);
            return ResponseEntity.ok("Course " + id + " deleted successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
