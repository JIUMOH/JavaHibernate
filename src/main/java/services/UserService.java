package services;

import dao.UserDao;
import models.Book;
import models.User;

import java.util.List;

public class UserService {

    private UserDao usersDao = new UserDao();

    public UserService() {
    }

    public User findUser(String username) {
        List<User> users = usersDao.findAll();
        for (User u:users) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }

    public void saveUser(User user) {
        usersDao.save(user);
    }

    public void deleteUser(User user) {
        usersDao.delete(user);
    }

    public void updateUser(User user) {
        usersDao.update(user);
    }

    public List<User> findAllUsers() {
        return usersDao.findAll();
    }

    public Book findBookById(int id) {
        return usersDao.findBookById(id);
    }


}