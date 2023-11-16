package org.finalproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.finalproject.domain.Rubric;
import org.finalproject.service.CRUDService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class RubricControllerTest {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CRUDService<Rubric> service;

    @Test
    @WithMockUser(username = "sa", roles = {"ADMIN"})
    public void shouldSaveRubric() throws Exception {
        Mockito.doNothing().when(service).save(ArgumentMatchers.any(Rubric.class));

        Rubric rubric = Rubric
                .builder()
                .name("Car")
                .build();

        String json = OBJECT_MAPPER.writeValueAsString(rubric);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/rubrics/new")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "sa", roles = {"ADMIN"})
    public void shouldUpdateRubric() throws Exception {
        Mockito.doNothing().when(service).update(ArgumentMatchers.any(Rubric.class));

        Rubric rubric = Rubric
                .builder()
                .name("Car")
                .build();

        String json = OBJECT_MAPPER.writeValueAsString(rubric);

        mockMvc
                .perform(MockMvcRequestBuilders.patch("/rubrics/" + rubric.getId())
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "sa", roles = {"ADMIN"})
    public void shouldDeleteRubric() throws Exception {
        Mockito.doNothing().when(service).deleteById(ArgumentMatchers.any(Integer.class));

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/rubrics/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "sa", roles = {"USER"})
    public void shouldGetRubric() throws Exception {

        Rubric rubric = Rubric
                .builder()
                .name("Car")
                .build();

        Mockito.when(service.findById(ArgumentMatchers.anyInt())).thenReturn(rubric);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/rubrics/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Car"));

      /*  mockMvc
                .perform(get("/man/getman/{manId}", "12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name1").value("Jack"))
                .andExpect(jsonPath("$.age").value(41))
                .andExpect(jsonPath("$.phone.number").value("067"))
                .andExpect(jsonPath("$.emails[0].name").value("@mail.ru"))
                .andExpect(jsonPath("$.emails[1].name").value("@rambler.ru"));*/
    }

    @Test
    @WithMockUser(username = "sa", roles = {"USER"})
    public void shouldGetAllRubrics() throws Exception {

        Rubric rubric = Rubric
                .builder()
                .name("Car")
                .build();

        Rubric rubric1 = Rubric
                .builder()
                .id(1)
                .name("Phone")
                .build();

        List<Rubric> rubrics = new ArrayList<>();

        rubrics.add(rubric);
        rubrics.add(rubric1);

        Mockito.when(service.findAll()).thenReturn(rubrics);

        mockMvc
                .perform(MockMvcRequestBuilders.get("/rubrics"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("Car"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name").value("Phone"));
        Assertions.assertEquals(rubrics.size(), 2);
    }


}

