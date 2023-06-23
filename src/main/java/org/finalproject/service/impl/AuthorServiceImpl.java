package org.finalproject.service.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.dao.CRUDDao;
import org.finalproject.dao.impl.AuthorDAOImpl;
import org.finalproject.domain.Author;
import org.finalproject.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements CRUDService<Author> {
    @Autowired
    private CRUDDao<Author> dao;
    //public AuthorServiceImpl() {
    //    dao = new AuthorDAOImpl();
    //}

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

    @Override
    public Author findById(int id) {
        return dao.findById(id);
    }
}
