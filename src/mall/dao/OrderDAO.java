package mall.dao;

import mall.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAO extends BaseDAO {
    // ATTENTION: order is KEYWORD in SQL, therefore use `order` (not 'order') to represent the table 'order'

    public static boolean insertOrder(Integer userid) throws SQLException {
        if (UserDAO.selectByIduser(userid) == null)
            return false;
        BaseDAO.createConn();
        String sql = "insert into `order`(userid) values(?)";
        Object[] params = {userid};
        return BaseDAO.execInsUpdDel(sql, params) > 0;
    }

    public static boolean updateOrder(Order order) throws SQLException {
        if (UserDAO.selectByIduser(order.getUserid()) == null)
            return false;
        BaseDAO.createConn();
        String sql = "update `order` set userid=?,commit=?,email=? where idorder=?";
        Object[] params = {
                order.getUserid(),
                order.getCommit(),
                order.getEmail(),
                order.getIdorder()
        };
        return BaseDAO.execInsUpdDel(sql, params) > 0;
    }

    public static Order select(Integer idorder) throws SQLException {
        Order order = null;
        try {
            BaseDAO.createConn();
            String sql = "select * from `order` where idorder=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idorder);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                order = new Order(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3), rs.getInt(4), rs.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return order;
    }

    public static Order selectCartByUserid(Integer userid) throws SQLException {
        Order order = null;
        try {
            BaseDAO.createConn();
            String sql = "select * from `order` where userid=? and commit=0";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                order = new Order(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3), rs.getInt(4), rs.getString(5));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return order;
    }

    public static ArrayList<Integer> selectCommittedIdorders() throws SQLException {
        ArrayList<Integer> committedIdorders = new ArrayList<>();
        try {
            BaseDAO.createConn();
            String sql = "select idorder from `order` where commit=1";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                committedIdorders.add(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return committedIdorders;
    }

    public static ArrayList<Order> selectCommittedOrders() throws SQLException {
        ArrayList<Order> committedOrders = new ArrayList<>();
        try {
            BaseDAO.createConn();
            String sql = "select * from `order` where commit=1";
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                committedOrders.add(new Order(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3), rs.getInt(4), rs.getString(5)));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return committedOrders;
    }
}
