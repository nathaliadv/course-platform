package com.nadantas.courseplatform.functional;

import com.nadantas.courseplatform.adapter.inbound.http.course.dto.request.CreateCourseRequestDTO;
import com.nadantas.courseplatform.shared.Utils;
import com.nadantas.courseplatform.shared.enums.Category;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class CreateCourseFunctionalTest extends AbstractFunctionalTest {

    @Test
    public void shouldCreateACourse() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.post("/courses/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Utils.objectToJSON(createCourseRequestDTO())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Course registered successfully! Id: ")));
    }

    private CreateCourseRequestDTO createCourseRequestDTO() {
        return CreateCourseRequestDTO.builder()
                .name("Name Test Course")
                .category(Category.BACKEND)
                .description("Description Test Course")
                .active(true)
                .build();
    }
}
