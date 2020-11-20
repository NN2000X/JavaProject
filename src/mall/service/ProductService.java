package mall.service;

import mall.dao.CatalogDAO;
import mall.dao.ProductDAO;
import mall.entity.Product;

import java.sql.SQLException;

public class ProductService {
    public static boolean isProductValid(Product product) throws SQLException {
        return product.getName() != null &&
                !product.getName().isBlank() &&
                product.getPrice() != null &&
                product.getPrice() >= 0 &&
                product.getStock() != null &&
                product.getStock() >= 0 &&
                CatalogDAO.select(product.getCatalogid()) != null;
    }

    public static boolean reduceStock(Integer idproduct, Integer reduction) throws SQLException {
        Product product = ProductDAO.select(idproduct);
        product.setStock(product.getStock() - reduction);
        return isProductValid(product) && ProductDAO.updateProduct(product);
    }
}
