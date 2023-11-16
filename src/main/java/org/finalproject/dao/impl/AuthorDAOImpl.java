package org.finalproject.dao.impl;

import org.finalproject.dao.AuthorDAO;
import org.finalproject.domain.Author;
import org.finalproject.repository.AuthorRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class AuthorDAOImpl implements AuthorDAO {
    private final AuthorRepository authorRepository;
    public AuthorDAOImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void save(Author entity) {
        authorRepository.save(entity);
    }

    @Override
    public void update(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteById(int id) {
       authorRepository.deleteById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(int id) {
        return authorRepository.findById(id);
    }
    @Override
    public Optional<Author> findByEmail(String email){
        return authorRepository.findByEmailEmail(email);
    }
}
