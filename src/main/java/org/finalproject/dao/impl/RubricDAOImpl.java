package org.finalproject.dao.impl;

import org.finalproject.dao.CRUDDAO;
import org.finalproject.domain.Rubric;
import org.finalproject.repository.RubricRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class RubricDAOImpl implements CRUDDAO<Rubric> {
    private final RubricRepository rubricRepository;

    public RubricDAOImpl(RubricRepository rubricRepository) {
        this.rubricRepository = rubricRepository;
    }

    @Override
    public void save(Rubric rubric) {
        rubricRepository.save(rubric);
    }

    @Override
    public void update(Rubric rubric) {
        rubricRepository.save(rubric);
    }

    @Override
    public void deleteById(int id) {
        rubricRepository.deleteById(id);
    }

    @Override
    public List<Rubric> findAll() {
        return rubricRepository.findAll();
    }

    @Override
    public Optional<Rubric> findById(int id) {
        return rubricRepository.findById(id);
    }
}
