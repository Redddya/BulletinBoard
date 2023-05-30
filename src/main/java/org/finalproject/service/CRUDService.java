package org.finalproject.service;

import java.util.List;

public interface CRUDService<T> {
    public void save(T entity);
    public void update(T entity);
    void deleteById(int id);
    List<T> findAll();
}
