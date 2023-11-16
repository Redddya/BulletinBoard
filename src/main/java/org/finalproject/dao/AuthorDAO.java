package org.finalproject.dao;

import org.finalproject.domain.Author;

import java.util.Optional;

public interface AuthorDAO extends CRUDDAO<Author> {
    Optional<Author> findByEmail(String email);
}
