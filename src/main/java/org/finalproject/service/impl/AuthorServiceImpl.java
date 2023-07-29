package org.finalproject.service.impl;

import org.finalproject.dao.CRUDDao;
import org.finalproject.domain.Author;
import org.finalproject.service.CRUDService;
import org.finalproject.util.exception.custom.AuthorException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements CRUDService<Author> {
    private final CRUDDao<Author> dao;

    public AuthorServiceImpl(CRUDDao<Author> dao) {
        this.dao = dao;
    }

    @Override
    public void save(Author author) {
        dao.save(author);
    }

    @Override
    public void update(Author entity) {
        dao.update(entity);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<Author> findAll() {

        List<Author> authors = dao.findAll();
        if(authors.isEmpty())
            throw new AuthorException("Any Authors wasn't found");
        return authors;
    }

    @Override
    public Author findById(int id) {
        Author author = dao.findById(id);
        if (author == null)
            throw new AuthorException("Author with this id wasn't found");
        return author;
    }
}
