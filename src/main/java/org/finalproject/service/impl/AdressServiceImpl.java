package org.finalproject.service.impl;

import org.finalproject.dao.CRUDDao;
import org.finalproject.dao.impl.AdressDAOImpl;
import org.finalproject.domain.Adress;
import org.finalproject.service.CRUDService;

import java.util.List;

public class AdressServiceImpl implements CRUDService<Adress> {

    private CRUDDao<Adress> dao;
    public AdressServiceImpl() {
        dao = new AdressDAOImpl();
    }
    @Override
    public void save(Adress adress) {
    dao.save(adress);
    }

    @Override
    public void update(Adress entity) {
        dao.update(entity);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public List<Adress> findAll() {
        return dao.findAll();
    }

    @Override
    public Adress findById(int id) {
        return dao.findById(id);
    }

}
