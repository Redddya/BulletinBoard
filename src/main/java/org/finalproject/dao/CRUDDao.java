package org.finalproject.dao;

import java.util.List;

public interface CRUDDao<T> {
    public void save(T entity);
    public void update(T entity);
    void deleteById(int id);
    List<T> findAll();
    T findById(int id);
}
