package mall.dao;

import mall.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO {
    public static boolean insertUser(User user) throws SQLException {
        BaseDAO.createConn();
        String sql = "insert into user values(null,?,?)";
        Object[] params = {
                user.getUsername(),
                user.getPassword()
        };
        return BaseDAO.execInsUpdDel(sql, params) > 0;
    }

    public static boolean updateUser(User user) throws SQLException {
        BaseDAO.createConn();
        String sql = "update user set username=?,password=? where iduser=?";
        Object[] params = {
                user.getUsername(),
                user.getPassword(),
                user.getIduser()
        };
        return BaseDAO.execInsUpdDel(sql, params) > 0;
    }

    public static User selectByIduser(Integer iduser) throws SQLException {
        User user = null;
        try {
            BaseDAO.createConn();
            String sql = "select * from user where iduser=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, iduser);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return user;
    }

    public static User selectByUsername(String username) throws SQLException {
        User user = null;
        try {
            BaseDAO.createConn();
            String sql = "select * from user where username=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return user;
    }
}
