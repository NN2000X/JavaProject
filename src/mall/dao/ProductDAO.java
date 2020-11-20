package mall.dao;

import mall.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO extends BaseDAO {
    public static boolean insertProduct(Product product) throws SQLException {
        BaseDAO.createConn();
        String sql = "insert into product(name,price,stock,catalogid,description) values(?,?,?,?,?)";
        Object[] params = {
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCatalogid(),
                product.getDescription()
        };
        return BaseDAO.execInsUpdDel(sql, params) > 0;
    }

    public static boolean updateProduct(Product product) throws SQLException {
        BaseDAO.createConn();
        String sql = "update product set name=?,price=?,stock=?,catalogid=?,description=? where idproduct=?";
        Object[] params = {
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCatalogid(),
                product.getDescription(),
                product.getIdproduct()
        };
        return BaseDAO.execInsUpdDel(sql, params) > 0;
    }

    public static boolean deleteProduct(Integer idproduct) throws SQLException {
        BaseDAO.createConn();
        String sql = "delete from product where idproduct=?";
        Object[] params = {idproduct};
        return BaseDAO.execInsUpdDel(sql, params) > 0;
    }

    public static Product select(Integer idproduct) throws SQLException {
        Product product = null;
        try {
            BaseDAO.createConn();
            String sql = "select * from product where idproduct=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idproduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                product = new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return product;
    }

    public static ArrayList<Product> selectByCatalogid(Integer catalogid) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        try {
            BaseDAO.createConn();
            String sql = "select * from product where catalogid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, catalogid);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                products.add(new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getInt(5), rs.getString(6)));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return products;
    }

    public static ArrayList<Integer> selectIdproductsByCatalogid(Integer catalogid) throws SQLException {
        ArrayList<Integer> idproducts = new ArrayList<>();
        try {
            BaseDAO.createConn();
            String sql = "select idproduct from product where catalogid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, catalogid);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                idproducts.add(rs.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return idproducts;
    }

    public static Integer selectStock(Integer idproduct) throws SQLException {
        Integer stock = null;
        try {
            BaseDAO.createConn();
            String sql = "select stock from product where idproduct=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idproduct);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
                stock = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.close();
        }
        return stock;
    }
}
