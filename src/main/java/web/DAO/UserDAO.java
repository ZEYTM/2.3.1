package web.DAO;

import web.model.User;

import java.util.List;

public interface UserDAO {

    public List<User> getListUsers();

    public void saveUser(User user);

    public User getUser(int id);

    public void updateUser(int id, User updateUser);

    public void deleteUser(int id);

}
