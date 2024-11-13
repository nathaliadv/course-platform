package com.nadantas.courseplatform.adapter.inbound.http.course;

import com.nadantas.courseplatform.adapter.inbound.http.course.converter.AdapterConverter;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.request.CreateCourseRequestDTO;
import com.nadantas.courseplatform.core.ports.inbound.CreateCourseInboundPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Courses", description = "Related to the registration of new courses on the platform.")
public class CreateCourseController {

    private final CreateCourseInboundPort createCourseInboundPort;
    private final AdapterConverter converter;

    @PostMapping("/")
    @Operation(summary = "Create new course", description = "Allows you to register a new course.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Course registered successfully! Id: ${id}"),
            @ApiResponse(responseCode = "400", description = "Bad request, creation failed",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Object> execute(@Valid @RequestBody CreateCourseRequestDTO requestDto) {
        try {
            UUID courseId = createCourseInboundPort.execute(converter.convertToCourseModel(requestDto));
            return ResponseEntity.ok("Course registered successfully! Id: " + courseId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
