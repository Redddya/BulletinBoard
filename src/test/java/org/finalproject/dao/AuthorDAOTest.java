package org.finalproject.dao;

import org.finalproject.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AuthorDAOTest {

    @TestConfiguration
    @ComponentScan(basePackages = "org.finalproject.*")
    public static class Config {
    }

    @Autowired
    private AuthorDAO authorDAO;

//    @BeforeEach
    public void saveAuthor() {
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

        Author john = Author
                .builder()
                .address(address)
                .phone(phone)
                .email(email)
                .password("123")
                .role(Role.ROLE_ADMIN)
                .name("John")
                .build();

        authorDAO.save(john);
    }

    @Test
    void shouldSaveAuthor() {

        saveAuthor();

        Author author = authorDAO.findById(1).get();

        Assertions.assertEquals("123@gmail.com", author.getEmail().getEmail());
    }

    @Test
    void shouldDeleteAuthor(){
        saveAuthor();
        authorDAO.deleteById(1);
        boolean isAuthorExists = authorDAO.findById(1).isEmpty();

        Assertions.assertTrue(isAuthorExists);
    }

    @Test
    void shouldUpdateAuthor(){
        saveAuthor();
        String testEmail = "test@email.com";

        Author author = authorDAO.findById(1).get();
        author.setEmail(Email.builder().email(testEmail).build());
        authorDAO.update(author);

        Assertions.assertEquals(testEmail, author.getEmail().getEmail());
    }

    @Test
    void shouldFindByEmail(){
        saveAuthor();

        String email = "123@gmail.com";

        Author author = authorDAO.findByEmail(email).get();
        Assertions.assertEquals(email, author.getEmail().getEmail());
        Assertions.assertEquals("John", author.getName());
    }

    @Test
    void shouldFindByIdAuthor(){
        saveAuthor();

        String email = "123@gmail.com";

        Author author = authorDAO.findById(1).get();
        Assertions.assertEquals(email, author.getEmail().getEmail());
        Assertions.assertEquals("John", author.getName());
    }

    @Test
    void shouldFindAllAuthor(){

    }
}
