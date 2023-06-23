package org.finalproject.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public interface CRUDDao<T> {
    public void save(T entity);
    public void update(T entity);
    void deleteById(int id);
    List<T> findAll();
    T findById(int id);
}
