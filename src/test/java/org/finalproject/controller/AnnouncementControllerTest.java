package org.finalproject.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.finalproject.domain.*;
import org.finalproject.service.AnnouncementService;
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

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
public class AnnouncementControllerTest {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AnnouncementService service;

    @Test
    @WithMockUser(username = "sa", roles = {"USER"})
    public void shouldCreateAnnouncement() throws Exception {
        Mockito.doNothing().when(service).save(ArgumentMatchers.any(Announcement.class));
        Address address = Address
                .builder()
                .city("Kiev")
                .country("Ukraine")
                .street("Pushkina")
                .house("15A")
                .build();

        Phone phone = Phone
                .builder()
                .phone("067")
                .build();

        Email email = Email
                .builder()
                .email("123@gmail.com")
                .build();

        Author author = Author
                .builder()
                .address(address)
                .phone(phone)
                .email(email)
                .password("123")
                .role(Role.ROLE_USER)
                .name("John")
                .build();
        Rubric rubric = Rubric.builder()
                .name("Car")
                .build();
        Announcement announcement = Announcement.builder()
                .price(BigDecimal.TEN)
                .name("Car Nissan 1234567")
                .author(author)
                .text("Car description")
                .rubric(rubric)
                .build();

        String json = OBJECT_MAPPER.writeValueAsString(announcement);

        mockMvc
                .perform(MockMvcRequestBuilders.post("/announcements/new")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
