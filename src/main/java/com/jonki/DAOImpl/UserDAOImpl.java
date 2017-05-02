package com.jonki.DAOImpl;

import com.jonki.DAO.UserDAO;
import com.jonki.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean checkRepeatedUsername(final String username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from com.jonki.Entity.User e where username = ?";
        User user;
        try {
            Query query = session.createQuery(hql);
            query.setParameter(0, username);
            user = (User) query.uniqueResult();
            transaction.commit();
            session.close();

            if (user == null)
                return false;
            else
                return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean checkRepeatedEmail(final String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from com.jonki.Entity.User e where email = ?";
        User user;
        try {
            Query query = session.createQuery(hql);
            query.setParameter(0, email);
            user = (User) query.uniqueResult();
            transaction.commit();
            session.close();

            if (user == null)
                return false;
            else
                return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean checkActivationCode(final Long id, final int code) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, id);
            transaction.commit();
            session.close();

            return user.getActivationCode() == code;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public boolean checkChangeCode(final Long id, final int code) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            User user = session.get(User.class, id);
            transaction.commit();
            session.close();

            return user.getCodeChange() == code;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Long getIDByUsername(final String username) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from com.jonki.Entity.User e where username = ?";
        Long ID = null;
        try {
            Query query = session.createQuery(hql);
            query.setParameter(0, username);
            ID = ((User) query.uniqueResult()).getId();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
        }

        return ID;
    }

    @Override
    public Long getIDByEmail(final String email) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String hql = "from com.jonki.Entity.User e where email = ?";
        Long ID = null;
        try {
            Query query = session.createQuery(hql);
            query.setParameter(0, email);
            ID = ((User) query.uniqueResult()).getId();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
        }

        return ID;
    }
}
