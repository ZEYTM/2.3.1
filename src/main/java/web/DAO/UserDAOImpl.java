package web.DAO;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<User> getListUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
    @Override
    @Transactional
    public void saveUser(User user) {
        entityManager.persist(user);
    }
    @Override
    @Transactional(readOnly = true)
    public User getUser(int id) {
        return getListUsers().stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }
    @Override
    @Transactional
    public void updateUser(int id, User updateUser) {
        User user = getUser(id);
        user.setName(updateUser.getName());
        user.setAge(updateUser.getAge());
        entityManager.persist(user);
    }
    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = getUser(id);
        entityManager.remove(user);
    }

}
