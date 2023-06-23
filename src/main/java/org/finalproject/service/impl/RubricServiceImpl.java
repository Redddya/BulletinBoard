package org.finalproject.service.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.dao.CRUDDao;
import org.finalproject.dao.impl.RubricDAOImpl;
import org.finalproject.domain.Rubric;
import org.finalproject.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RubricServiceImpl implements CRUDService<Rubric> {
    @Autowired
    private CRUDDao<Rubric> dao;

    @Override
    public void save(Rubric rubric) {
        dao.save(rubric);
    }

    @Override
    public void update(Rubric entity) {
        dao.update(entity);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<Rubric> findAll() {
        return dao.findAll();
    }

    @Override
    public Rubric findById(int id) {
        return dao.findById(id);
    }
}
