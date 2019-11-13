package org.mycompany.myname.database;

import org.mycompany.myname.model.UserProfile;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;

public class UserProfileDaoHib implements IUserProfileDao{
    @Override
    public void addUser(String login, String email, String pass) {
        UserProfile user = new UserProfile(login, pass, email);
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public UserProfile findUserByLoginOrEmail(String login) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
            Query query = session.createQuery("FROM UserProfile where login = :login");
            query.setParameter("login", login);
            List usersList =  query.getResultList();
            if (usersList.isEmpty()){
                query = session.createQuery("FROM UserProfile where email = :email");
                query.setParameter("email", login);
                usersList = query.getResultList();
            }
            return (UserProfile)usersList.get(0);
        } catch (Exception e) {
            return null;
        }
    }
}
