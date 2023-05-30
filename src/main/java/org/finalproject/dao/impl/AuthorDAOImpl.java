package org.finalproject.dao.impl;

import org.finalproject.dao.AnnouncementDAO;
import org.finalproject.dao.CRUDDao;
import org.finalproject.domain.Adress;
import org.finalproject.domain.Announcement;
import org.finalproject.domain.Author;
import org.finalproject.domain.Rubric;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class AuthorDAOImpl implements CRUDDao<Author> {

    AnnouncementDAO dao = new AnnouncementDAOImpl();

    @Override
    public void save(Author entity) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(entity);
        transaction.commit();
    }

    @Override
    public void update(Author author) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Author author1 = entityManager.merge(author);
        entityManager.persist(author1);
        transaction.commit();
    }

    @Override
    public void deleteById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        dao.deleteAllByAuthorId(id);
        Query query = entityManager
                .createQuery("DELETE Author a WHERE a.id = :author_id");
        query.setParameter("author_id", id);
        query.executeUpdate();
        transaction.commit();

    }

    @Override
    public List<Author> findAll() {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        TypedQuery<Author> query = entityManager.createQuery("FROM Author a", Author.class);
        List<Author> authors = query.getResultList();
        transaction.commit();
        return authors;
    }

    @Override
    public Author findById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Author author = entityManager.find(Author.class, id);
        transaction.commit();
        return author;
    }
}
