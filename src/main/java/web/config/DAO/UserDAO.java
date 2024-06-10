package web.config.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> getListUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public User showUser(int id) {
        return getListUsers().stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }
    @Transactional
    public void update(int id, User updateUser){
        User user = showUser(id);
        user.setName(updateUser.getName());
        user.setAge(updateUser.getAge());
        entityManager.persist(user);
    }




}
