package mall.dao;

import mall.entity.Detail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetailDAO extends BaseDAO {
    public static boolean insertDetail(Detail detail) throws SQLException {
        BaseDAO.createConn();
        String sql = "insert into detail(orderid,productid,quantity) values(?,?,?)";
        Object[] params = {
                detail.getOrderid(),
                detail.getProductid(),
                detail.getQuantity()
        };
        return BaseDAO.execInsUpdDel(sql, params) > 0;
    }

    public static boolean updateDetail(Detail detail) throws SQLException {
        BaseDAO.createConn();
        String sql = "update detail set orderid=?,productid=?,quantity=? where iddetail=?";
        Object[] params = {
                detail.getOrderid(),
                detail.getProductid(),
                detail.getQuantity(),
                detail.getIddetail()
        };
        return BaseDAO.execInsUpdDel(sql, params) > 0;
    }

    public static Detail select(Integer iddetail) throws SQLException {
        Detail detail = null;
        try {
            BaseDAO.createConn();
            String sql = "select * from detail where iddetail=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, iddetail);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                detail = new Detail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return detail;
    }

    public static Detail selectDetailByProduct(Integer orderid, Integer productid) throws SQLException {
        Detail detail = null;
        try {
            BaseDAO.createConn();
            String sql = "select * from detail where orderid=? and productid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderid);
            ps.setInt(2, productid);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                detail = new Detail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return detail;
    }

    public static ArrayList<Detail> selectByOrderid(Integer orderid) throws SQLException {
        ArrayList<Detail> details = new ArrayList<>();
        try {
            BaseDAO.createConn();
            String sql = "select * from detail where orderid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderid);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                details.add(new Detail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return details;
    }

    public static ArrayList<Integer> selectProductidsByOrderid(Integer orderid) throws SQLException {
        ArrayList<Integer> productids = new ArrayList<>();
        try {
            BaseDAO.createConn();
            String sql = "select productid from detail where orderid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, orderid);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                productids.add(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return productids;
    }

    public static ArrayList<Detail> selectByProductid(Integer productid) throws SQLException {
        ArrayList<Detail> details = new ArrayList<>();
        try {
            BaseDAO.createConn();
            String sql = "select * from detail where productid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productid);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                details.add(new Detail(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return details;
    }
}
