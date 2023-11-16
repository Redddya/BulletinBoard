package org.finalproject.dao;

import org.finalproject.domain.Author;
import org.finalproject.domain.Rubric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class RubricDAOTest {

    @TestConfiguration
    @ComponentScan(basePackages = "org.finalproject.*")
    public static class Config {
        @Bean(name = "mvcHandlerMappingIntrospector")
        public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
            return new HandlerMappingIntrospector();
        }
    }

    @Autowired
    private CRUDDAO<Rubric> rubricDAO;

    @BeforeEach
    public void saveRubric() {
        Rubric rubric = Rubric.builder().name("Rubric").build();
        rubricDAO.save(rubric);
    }

    @Test
    public void shouldSaveRubric(){
        Rubric rubric = rubricDAO.findById(0).get();
        Assertions.assertEquals("Rubric", rubric.getName());
    }


}