package org.finalproject.dao;

import java.util.List;
import java.util.Optional;

public interface CRUDDAO<T> {
    public void save(T entity);
    public void update(T entity);
    void deleteById(int id);
    List<T> findAll();
    Optional<T> findById(int id);
}
