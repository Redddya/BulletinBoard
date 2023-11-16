package org.finalproject.repository;

import org.finalproject.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("SELECT a from Author a WHERE a.email.email = :email")
    Optional<Author> findByEmailEmail(@Param("email") String email);
}
