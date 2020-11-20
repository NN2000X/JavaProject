package mall.service;

import mall.dao.CatalogDAO;
import mall.dao.DetailDAO;
import mall.dao.OrderDAO;
import mall.dao.ProductDAO;
import mall.entity.Catalog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CatalogService {
    public static boolean isCatalogValid(Catalog catalog) throws SQLException {
        return catalog.getName() != null && !catalog.getName().isBlank() && CatalogDAO.select(catalog.getParentid()) != null;
    }

    public static boolean addCatalog(String newName, Integer parentid) throws SQLException {
        return CatalogDAO.insertCatalog(new Catalog(null, newName, parentid));
    }

    public static boolean modifyName(Integer idcatalog, String newName) throws SQLException {
        Catalog catalog = CatalogDAO.select(idcatalog);
        catalog.setName(newName);
        return CatalogDAO.updateCatalog(catalog);
    }

    public static boolean removeCatalog(Integer idcatalog) throws SQLException {
        // 此处dirtyProducts指有人购买了的商品
        ArrayList<Integer> committedOrders = OrderDAO.selectCommittedIdorders();
        Set<Integer> dirtyProducts = new HashSet<>();
        for (int i = 0; i < committedOrders.size(); i++)
            dirtyProducts.addAll(DetailDAO.selectProductidsByOrderid(committedOrders.get(i)));

        ArrayList<Integer> catalogs = new ArrayList<>();
        ArrayList<Integer> products = new ArrayList<>();
        catalogs.add(idcatalog);

        // 此处products为将要删除的分类下的商品
        for (int i = 0; i < catalogs.size(); i++)
            if (isEndCatalog(idcatalog))
                products.addAll(ProductDAO.selectIdproductsByCatalogid(catalogs.get(i)));
            else
                catalogs.addAll(CatalogDAO.selectIdcatalogsByParentid(catalogs.get(i)));
        // 如果将要删除的商品里有已经被购买的，则禁止删除
        for (int i = 0; i < products.size(); i++)
            if (dirtyProducts.contains(products.get(i)))
                return false;
        // 如果整个分类全部安全，则可以删除
        for (int i = 0; i < products.size(); i++)
            ProductDAO.deleteProduct(products.get(i));
        for (int i = 0; i < catalogs.size(); i++)
            CatalogDAO.deleteCatalog(catalogs.get(i));
        return true;
    }

    public static boolean isEndCatalog(Integer idcatalog) throws SQLException {
        return CatalogDAO.selectByParentid(idcatalog).isEmpty();
    }

    public static boolean isEmptyCatalog(Integer idcatalog) throws SQLException {
        return CatalogDAO.selectByParentid(idcatalog).isEmpty() && ProductDAO.selectByCatalogid(idcatalog).isEmpty();
    }
}
