package mall.dao;

import mall.entity.Catalog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CatalogDAO extends BaseDAO {
    public static boolean insertCatalog(Catalog catalog) throws SQLException {
        BaseDAO.createConn();
        String sql = "insert into catalog(name,parentid) values(?,?)";
        Object[] params = {
                catalog.getName(),
                catalog.getParentid()
        };
        return BaseDAO.execInsUpdDel(sql, params) > 0;
    }

    public static boolean updateCatalog(Catalog catalog) throws SQLException {
        BaseDAO.createConn();
        String sql = "update catalog set name=?,parentid=? where idcatalog=?";
        Object[] params = {
                catalog.getName(),
                catalog.getParentid(),
                catalog.getIdcatalog()
        };
        return BaseDAO.execInsUpdDel(sql, params) > 0;
    }

    public static boolean deleteCatalog(Integer idcatalog) throws SQLException {
        BaseDAO.createConn();
        String sql = "delete from catalog where idcatalog=?";
        Object[] params = {idcatalog};
        return BaseDAO.execInsUpdDel(sql, params) > 0;
    }

    public static Catalog select(Integer idcatalog) throws SQLException {
        Catalog catalog = null;
        try {
            BaseDAO.createConn();
            String sql = "select * from catalog where idcatalog=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idcatalog);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                catalog = new Catalog(rs.getInt(1), rs.getString(2), rs.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return catalog;
    }

    public static Catalog selectByName(String name) throws SQLException {
        Catalog catalog = null;
        try {
            BaseDAO.createConn();
            String sql = "select * from catalog where name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                catalog = new Catalog(rs.getInt(1), rs.getString(2), rs.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return catalog;
    }

    public static ArrayList<Catalog> selectByParentid(Integer parentid) throws SQLException {
        ArrayList<Catalog> catalogs = new ArrayList<>();
        try {
            BaseDAO.createConn();
            String sql = "select * from catalog where parentid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, parentid);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                catalogs.add(new Catalog(rs.getInt(1), rs.getString(2), rs.getInt(3)));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return catalogs;
    }

    public static ArrayList<Integer> selectIdcatalogsByParentid(Integer parentid) throws SQLException {
        ArrayList<Integer> idcatalogs = new ArrayList<>();
        try {
            BaseDAO.createConn();
            String sql = "select idcatalog from catalog where parentid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, parentid);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                idcatalogs.add(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return idcatalogs;
    }

    public static Integer selectParentid(Integer idcatalog) throws SQLException {
        Integer parentid = null;
        try {
            BaseDAO.createConn();
            String sql = "select parentid from catalog where idcatalog=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idcatalog);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                parentid = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return parentid;
    }

    public static Integer selectIdcatalogByName(String name) throws SQLException {
        Integer idcatalog = null;
        try {
            BaseDAO.createConn();
            String sql = "select idcatalog from catalog where name=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                idcatalog = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return idcatalog;
    }
}
