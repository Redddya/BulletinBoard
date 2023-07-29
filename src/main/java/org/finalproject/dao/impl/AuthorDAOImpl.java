package org.finalproject.dao.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.dao.CRUDDao;
import org.finalproject.domain.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Transactional
@Repository
public class AuthorDAOImpl implements CRUDDao<Author> {
    private final AnnouncementDAO dao;
    @PersistenceContext
    private EntityManager em;

    public AuthorDAOImpl(AnnouncementDAO dao) {
        this.dao = dao;
    }

    @Override
    public void save(Author entity) {
        em.persist(entity);
    }

    @Override
    public void update(Author author) {
        Author author1 = em.merge(author);
        em.persist(author1);
    }

    @Override
    public void deleteById(int id) {
        dao.deleteAllByAuthorId(id);
        Query query = em
                .createQuery("DELETE Author a WHERE a.id = :author_id");
        query.setParameter("author_id", id);
        query.executeUpdate();
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("FROM Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public Author findById(int id) {
        return em.find(Author.class, id);
    }
}
