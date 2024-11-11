package com.nadantas.courseplatform.adapter.inbound.http.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nadantas.courseplatform.adapter.inbound.http.course.converter.AdapterConverter;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.request.CreateCourseRequestDTO;
import com.nadantas.courseplatform.core.ports.inbound.CreateCourseInboundPort;
import com.nadantas.courseplatform.shared.enums.Category;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CreateCourseControllerTest {

  @Mock
  private CreateCourseInboundPort createCourseInboundPort;

  @Mock
  private AdapterConverter converter;

  @InjectMocks
  private CreateCourseController createCourseController;

  private CreateCourseRequestDTO requestDto;

  @BeforeEach
  void setUp() {
    requestDto = CreateCourseRequestDTO.builder()
        .name("Course 1")
        .category(Category.BACKEND)
        .active(true)
        .description("Esse é um livro sobre Java")
        .build();
  }

  @Test
  void shouldReturnOk_whenCreateCourse() {
    UUID courseId = UUID.randomUUID();
    when(createCourseInboundPort.execute(any())).thenReturn(courseId);

    ResponseEntity<Object> response = createCourseController.execute(requestDto);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("Course registered successfully! Id: " + courseId, response.getBody());
    verify(createCourseInboundPort).execute(any());
  }

  @Test
  void shouldReturnBadRequest_whenExceptionThrown() {
    when(createCourseInboundPort.execute(any())).thenThrow(new RuntimeException("Error"));

    ResponseEntity<Object> response = createCourseController.execute(requestDto);

    assertEquals(400, response.getStatusCodeValue());
    assertEquals("Error", response.getBody());
  }
}