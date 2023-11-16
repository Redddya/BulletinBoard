package org.finalproject.service.impl;

import org.finalproject.dao.AuthorDAO;
import org.finalproject.domain.Author;
import org.finalproject.service.AuthorService;
import org.finalproject.util.exception.custom.AuthorException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDAO authorDAO;
    private final PasswordEncoder encoder;

    public AuthorServiceImpl(@Qualifier("authorDAOImpl") AuthorDAO authorDAO, PasswordEncoder encoder) {
        this.authorDAO = authorDAO;
        this.encoder = encoder;
    }

    @Override
    public void save(Author author) {
        author.setPassword(encoder.encode(author.getPassword()));
        authorDAO.save(author);
    }

    @Override
    public void update(Author entity) {
        authorDAO.save(entity);
    }

    @Override
    public void deleteById(int id) {
        authorDAO.deleteById(id);
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = authorDAO.findAll();
        if (authors.isEmpty())
            throw new AuthorException("Any Authors wasn't found");
        return authors;
    }

    @Override
    public Author findById(int id) {
        Optional<Author> foundAuthor = authorDAO.findById(id);
        if (foundAuthor.isEmpty())
            throw new AuthorException("Author with this id wasn't found");
        return foundAuthor.get();
    }
    public Author findByEmail(String email){
        Optional<Author> foundAuthor = authorDAO.findByEmail(email);
        if (foundAuthor.isEmpty())
            throw new AuthorException("Author with this email wasn't found");
        return foundAuthor.get();
    }
}
