package org.example.dao;

import org.example.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getAllUsers() {
//        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
//        return query.getResultList();

        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public User getUserById(int id) {
//        TypedQuery<User> query = sessionFactory.getCurrentSession()
//                            .createQuery("from User where id = :paramId");
//        query.setParameter("paramId", id);
//        return query.getSingleResult();
        Query query = entityManager.createQuery("SELECT i from User i where i.id = :paramId");
        query.setParameter("paramId", id);
        return (User) query.getSingleResult();
    }

    @Override
    public void save(User user) {
//        sessionFactory.getCurrentSession().save(user);
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User user) {
        User userUpdate = getUserById(id);

        userUpdate.setName(user.getName());
        userUpdate.setAge(user.getAge());
        userUpdate.setCity(user.getCity());
        userUpdate.setEmail(user.getEmail());

        entityManager.merge(userUpdate);

//        sessionFactory.getCurrentSession().update(userUpdate);

    }

    @Override
    public void remove(int id) {
//        User user = new User();
//        user.setId(id);

        Query query = entityManager.createQuery("DELETE FROM User u WHERE u.id = :paramId");
        query.setParameter("paramId", id).executeUpdate();

//        sessionFactory.getCurrentSession().delete(user);
    }
}
