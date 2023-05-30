package org.finalproject.service.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.dao.CRUDDao;
import org.finalproject.dao.impl.AuthorDAOImpl;
import org.finalproject.domain.Author;
import org.finalproject.service.CRUDService;

import java.util.List;

public class AuthorServiceImpl implements CRUDService<Author> {
    private CRUDDao<Author> dao;
    public AuthorServiceImpl() {
        dao = new AuthorDAOImpl();
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
        return dao.findAll();
    }
}
