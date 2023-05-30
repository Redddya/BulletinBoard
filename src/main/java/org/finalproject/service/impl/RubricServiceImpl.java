package org.finalproject.service.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.dao.CRUDDao;
import org.finalproject.dao.impl.RubricDAOImpl;
import org.finalproject.domain.Rubric;
import org.finalproject.service.CRUDService;

import java.util.List;

public class RubricServiceImpl implements CRUDService<Rubric> {
    private CRUDDao<Rubric> dao;
    public RubricServiceImpl(){
        dao = new RubricDAOImpl();
    }

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
        return null;
    }
}
