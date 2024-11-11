package com.nadantas.courseplatform.adapter.inbound.http.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import com.nadantas.courseplatform.core.ports.inbound.DeleteCourseInboundPort;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class DeleteCourseControllerTest {

  @Mock
  private DeleteCourseInboundPort deleteCourseInboundPort;

  @InjectMocks
  private DeleteCourseController deleteCourseController;

  private UUID courseId;

  @BeforeEach
  void setUp() {
    courseId = UUID.randomUUID();
  }

  @Test
  void shouldReturnOk_whenCourseDeletedSuccessfully() {
    ResponseEntity<Object> response = deleteCourseController.execute(courseId);

    assertEquals(200, response.getStatusCodeValue());
    assertEquals("Course " + courseId + " deleted successfully!", response.getBody());
    verify(deleteCourseInboundPort).execute(courseId);
  }

  @Test
  void shouldReturnBadRequest_whenExceptionThrown() {
    doThrow(new RuntimeException("Error")).when(deleteCourseInboundPort).execute(courseId);

    ResponseEntity<Object> response = deleteCourseController.execute(courseId);

    assertEquals(400, response.getStatusCodeValue());
    assertEquals("Error", response.getBody());
  }
}