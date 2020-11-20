package mall.service;

import mall.dao.UserDAO;
import mall.entity.User;

import java.sql.SQLException;

public class UserService {
    public static boolean isUserValid(User user) {
        return user.getUsername() != null &&
                !user.getUsername().isBlank() &&
                user.getPassword() != null &&
                !user.getPassword().isBlank();
    }

    public static boolean isUsernameOccupied(String username) throws SQLException {
        User user = UserDAO.selectByUsername(username);
        return user != null;
    }

    public static User userLogin(User user) throws SQLException {
        if (!isUserValid(user))
            return null;
        User realUser = UserDAO.selectByUsername(user.getUsername());
        if (realUser != null && realUser.getPassword().equals(user.getPassword()))
            return realUser;
        return null;
    }

    public static boolean userUpdate(User user) throws SQLException {
        return isUserValid(user) && UserDAO.updateUser(user);
    }
}
