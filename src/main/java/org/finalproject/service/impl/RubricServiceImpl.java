package org.finalproject.service.impl;

import org.finalproject.dao.CRUDDAO;
import org.finalproject.domain.Rubric;
import org.finalproject.service.CRUDService;
import org.finalproject.util.exception.custom.RubricException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RubricServiceImpl implements CRUDService<Rubric> {
    private final CRUDDAO<Rubric> rubricDao;

    public RubricServiceImpl(CRUDDAO<Rubric> rubricDao) {
        this.rubricDao = rubricDao;
    }

    @Override
    public void save(Rubric rubric) {
        rubricDao.save(rubric);
    }

    @Override
    public void update(Rubric entity) {
        rubricDao.save(entity);
    }

    @Override
    public void deleteById(int id) {
        rubricDao.deleteById(id);
    }

    @Override
    public List<Rubric> findAll() {
        List<Rubric> rubrics = rubricDao.findAll();
        if (rubrics.isEmpty())
            throw new RubricException("Rubrics wasn't found");
        return rubrics;
    }

    @Override
    public Rubric findById(int id) {
        Optional<Rubric> foundRubric = rubricDao.findById(id);
        if (foundRubric.isEmpty())
            throw new RubricException("Rubric with this id wasn't found");
        return foundRubric.get();
    }
}
