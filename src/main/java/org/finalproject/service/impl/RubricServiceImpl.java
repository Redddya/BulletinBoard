package org.finalproject.service.impl;

import org.finalproject.dao.CRUDDao;
import org.finalproject.domain.Rubric;
import org.finalproject.service.CRUDService;
import org.finalproject.util.exception.custom.RubricException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RubricServiceImpl implements CRUDService<Rubric> {
    private final CRUDDao<Rubric> dao;

    public RubricServiceImpl(CRUDDao<Rubric> dao) {
        this.dao = dao;
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
        List<Rubric> rubrics = dao.findAll();
        if(rubrics.isEmpty())
                throw new RubricException("Rubrics wasn't found");
        return rubrics;
    }

    @Override
    public Rubric findById(int id) {
        Rubric rubric = dao.findById(id);
        if(rubric == null)
            throw new RubricException("Rubric with this id wasn't found");
        return rubric;
    }
}
