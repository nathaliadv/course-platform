package com.nadantas.courseplatform.functional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nadantas.courseplatform.core.model.CourseModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class GetCourseFunctionalTest extends AbstractFunctionalTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetAllCourses() throws Exception {
        String responseJson = mvc.perform(
                        MockMvcRequestBuilders.get("/courses/all")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<CourseModel> courses = objectMapper.readValue(responseJson, objectMapper.getTypeFactory().constructCollectionType(List.class, CourseModel.class));

        assertEquals(9, courses.size());

        assertEquals(UUID.fromString("fcc3c859-7812-4a2f-bddc-d7f8ebc3e808"), courses.get(0).id());
        assertEquals("The Complete Java Certification Course", courses.get(0).name());
        assertEquals("BACKEND", courses.get(0).category().toString());
    }

    @Test
    public void shouldGetAllCoursesThatNameContainsJava() throws Exception {
        String responseJson = mvc.perform(
                        MockMvcRequestBuilders.get("/courses/all/filter")
                                .param("name", "java")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<CourseModel> courses = objectMapper.readValue(responseJson, objectMapper.getTypeFactory().constructCollectionType(List.class, CourseModel.class));

        assertEquals(3, courses.size());

        assertEquals(UUID.fromString("fcc3c859-7812-4a2f-bddc-d7f8ebc3e808"), courses.get(0).id());
        assertEquals(UUID.fromString("6a87f0be-1858-47b1-bd7b-bc466f35d4c1"), courses.get(1).id());
        assertEquals(UUID.fromString("7ecd4fc2-2faf-46e2-b124-51eee7d2a56e"), courses.get(2).id());
    }

    @Test
    public void shouldGetAllCoursesThatCategoryIsBackend() throws Exception {
        String responseJson = mvc.perform(
                        MockMvcRequestBuilders.get("/courses/all/filter")
                                .param("category", "backend")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<CourseModel> courses = objectMapper.readValue(responseJson, objectMapper.getTypeFactory().constructCollectionType(List.class, CourseModel.class));

        assertEquals(4, courses.size());

        assertEquals(UUID.fromString("fcc3c859-7812-4a2f-bddc-d7f8ebc3e808"), courses.get(0).id());
        assertEquals(UUID.fromString("6a87f0be-1858-47b1-bd7b-bc466f35d4c1"), courses.get(1).id());
        assertEquals(UUID.fromString("06710d0d-1d56-4532-ad8b-a093cee6014d"), courses.get(2).id());
        assertEquals(UUID.fromString("aef134fd-82b2-49cf-b668-1538c6a2275a"), courses.get(3).id());
    }

    @Test
    public void shouldGetAllCoursesThatNameContainsHTMLCategoryIsFrontend() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "HTML");
        params.add("category", "frontend");

        String responseJson = mvc.perform(
                        MockMvcRequestBuilders.get("/courses/all/filter")
                                .params(params)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<CourseModel> courses = objectMapper.readValue(responseJson, objectMapper.getTypeFactory().constructCollectionType(List.class, CourseModel.class));

        assertEquals(1, courses.size());

        assertEquals(UUID.fromString("8940655b-8b46-4973-8800-f3580e3653f8"), courses.get(0).id());
    }

    @Test
    public void shouldGetAllCoursesWhenTheFiltersAreNull() throws Exception {
        String responseJson = mvc.perform(
                        MockMvcRequestBuilders.get("/courses/all/filter")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<CourseModel> courses = objectMapper.readValue(responseJson, objectMapper.getTypeFactory().constructCollectionType(List.class, CourseModel.class));

        assertEquals(9, courses.size());
    }
}
