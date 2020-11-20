package mall.dao;

import java.sql.*;

public class BaseDAO {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://120.24.21.191:3306/mall";
    public static final String USER = "root";
    public static final String PASSWORD = "password";

    public static Connection conn = null;
    public static PreparedStatement ps = null;

    public static void createConn() throws SQLException {
        if (conn != null)
            return;
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // executing operations like insert, update or delete
    public static int execInsUpdDel(String sql, Object[] params) throws SQLException {
        int count = 0;
        try {
            createConn();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++)
                ps.setObject(i + 1, params[i]);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return count;
    }

    public static void close() throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }
        if (ps != null) {
            ps.close();
            ps = null;
        }
    }
}
