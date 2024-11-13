package com.nadantas.courseplatform.adapter.inbound.http.course;

import com.nadantas.courseplatform.adapter.inbound.http.course.converter.AdapterConverter;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.request.ToggleActiveRequest;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.request.UpdateCourseRequestDTO;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.response.CourseResponseDTO;
import com.nadantas.courseplatform.core.model.CourseModel;
import com.nadantas.courseplatform.core.ports.inbound.UpdateCourseInboundPort;
import com.nadantas.courseplatform.shared.exceptions.CourseNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
@Tag(name = "Courses", description = "Related to updating attributes in an existing course.")
public class UpdateCourseController {

    private final UpdateCourseInboundPort updateCourseInboundPort;
    private final AdapterConverter converter;

    @PutMapping("/{id}")
    @Operation(summary = "Update course", description = "Allows you to update attributes from course by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the course",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseResponseDTO.class))),
            @ApiResponse(responseCode = "400", description =  "Bad request, the update failed",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Object> execute(@PathVariable UUID id, @RequestBody UpdateCourseRequestDTO updateCourseRequestDTO) {
        try {
            CourseModel courseUpdated = updateCourseInboundPort.execute(id, converter.convertToCourseModel(updateCourseRequestDTO));
            return ResponseEntity.ok(converter.toResponseDTO(courseUpdated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/active")
    @Operation(summary = "Toggle the active status of a course by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully toggled the course active status",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Course not found with the specified ID",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseNotFoundException.class))),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid input or toggle status",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error while saving the updated course status",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Object> executeToToggleActiveStatus(@PathVariable UUID id, @RequestBody ToggleActiveRequest toggleActiveRequest) {
        try {
            CourseModel courseUpdated = updateCourseInboundPort.executeToToggleActive(id, toggleActiveRequest.active());
            return ResponseEntity.ok(converter.toResponseDTO(courseUpdated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
