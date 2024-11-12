package com.nadantas.courseplatform.functional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.request.UpdateCourseRequestDTO;
import com.nadantas.courseplatform.adapter.inbound.http.course.dto.response.CourseResponseDTO;
import com.nadantas.courseplatform.shared.Utils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class UpdateCourseFunctionalTest extends AbstractFunctionalTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldUpdateCourseByID() throws Exception {
        String responseJson = mvc.perform(
                        MockMvcRequestBuilders.put("/courses/145121b7-e4b2-499c-a876-a6e09e631e98")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(Utils.objectToJSON(createRequest())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        CourseResponseDTO course = objectMapper.readValue(responseJson, objectMapper.getTypeFactory().constructType(CourseResponseDTO.class));

        assertEquals(UUID.fromString("145121b7-e4b2-499c-a876-a6e09e631e98"), course.id());
        assertEquals("Test Name", course.name());
        assertEquals("Test Description", course.description());
        assertEquals("DEVOPS", course.category().toString());
    }

    @Test
    public void shouldReturnBadRequestForInvalidCourseId() throws Exception {
        mvc.perform(
                        MockMvcRequestBuilders.put("/courses/f2dc4de3-1bee-44f9-ad76-350178ea5acb")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(Utils.objectToJSON(createRequest())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(content().string("Course not found with id: f2dc4de3-1bee-44f9-ad76-350178ea5acb"));
    }

    @Test
    public void shouldUpdateStatusActiveByID() throws Exception {
        String updatedContent = """
        {
            "active": true
        }""";

        String responseJson = mvc.perform(
                        MockMvcRequestBuilders.patch("/courses/145121b7-e4b2-499c-a876-a6e09e631e98/active")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updatedContent))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        CourseResponseDTO course = objectMapper.readValue(responseJson, objectMapper.getTypeFactory().constructType(CourseResponseDTO.class));

        assertEquals(UUID.fromString("145121b7-e4b2-499c-a876-a6e09e631e98"), course.id());
        assertEquals(true, course.active());
    }

    private UpdateCourseRequestDTO createRequest(){
         return UpdateCourseRequestDTO.builder()
                .name("Test Name")
                .description("Test Description")
                .build();
    }
}
