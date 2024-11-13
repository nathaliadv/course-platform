package com.nadantas.courseplatform.adapter.inbound.http.course;

import com.nadantas.courseplatform.adapter.inbound.http.course.converter.AdapterConverter;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.response.CourseResponseDTO;
import com.nadantas.courseplatform.core.model.CourseModel;
import com.nadantas.courseplatform.core.ports.inbound.GetCourseInboundPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
@Tag(name = "Courses", description = "Related to searching for courses on the platform.")
public class GetCourseController {

    private final GetCourseInboundPort getCourseInboundPort;
    private final AdapterConverter converter;

    @GetMapping("/all")
    @Operation(summary = "Search courses", description = "Allows you to search all registered courses.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of courses",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CourseResponseDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity<Object> execute() {
        try {
            List<CourseModel> listCourseResponseDTO = getCourseInboundPort.execute();
            return ResponseEntity.ok(converter.toResponseDTOList(listCourseResponseDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/all/filter")
    @Operation(summary = "Search courses by filter", description = "Allows you to search courses by filter name and/or category.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of courses by filter",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CourseResponseDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)))
    })
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
