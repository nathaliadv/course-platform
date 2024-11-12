package com.nadantas.courseplatform.functional;

import com.nadantas.courseplatform.adapter.inbound.http.course.dto.request.CreateCourseRequestDTO;
import com.nadantas.courseplatform.shared.Utils;
import com.nadantas.courseplatform.shared.enums.Category;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateCourseFunctionalTest extends AbstractFunctionalTest {

    @Test
    public void shouldCreateACourseAndDelete() throws Exception {
        String responseJson = mvc.perform(
                        MockMvcRequestBuilders.post("/courses/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(Utils.objectToJSON(createCourseRequestDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        String courseId = extractCourseIdFromResponse(responseJson);
        assertNotNull(courseId);

         responseJson = mvc.perform(
                MockMvcRequestBuilders.delete("/courses/" + courseId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertEquals(String.format("Course %s deleted successfully!", courseId), responseJson);

    }

    private CreateCourseRequestDTO createCourseRequestDTO() {
        return CreateCourseRequestDTO.builder()
                .name("Name Test Course")
                .category(Category.BACKEND)
                .description("Description Test Course")
                .active(true)
                .build();
    }

    private String extractCourseIdFromResponse(String response) {
        String prefix = "Course registered successfully! Id: ";
        if (response.startsWith(prefix)) {
            return response.substring(prefix.length());
        }
        throw new IllegalArgumentException("Response format is unexpected: " + response);
    }
}
