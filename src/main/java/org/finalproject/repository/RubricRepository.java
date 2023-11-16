package org.finalproject.repository;

import org.finalproject.domain.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RubricRepository extends JpaRepository<Rubric, Integer> {
}
