package org.finalproject.service;

import org.finalproject.domain.Author;

import java.util.Optional;

public interface AuthorService extends CRUDService<Author> {
    Author findByEmail(String email);
}
