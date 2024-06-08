package web.config.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class UserDAO {

    private EntityManagerFactory entityManagerFactory;

    public UserDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    public List<User> getListUsers() {
        List<User> userList = entityManagerFactory.createEntityManager().createQuery("select u from User u", User.class).getResultList();
        return userList;
    }

    @Transactional
    public void saveUser(User user) {
        entityManagerFactory.createEntityManager().persist(user);
    }


    //    private SessionFactory sessionFactory;
//
//    public UserDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    @Transactional
//    public List<User> getListUsers() {
//        Session session = sessionFactory.getCurrentSession();
//        List<User> userList = session.createQuery("select u from User u", User.class).getResultList();
//        return userList;
//    }
}
