package mall.service;

import mall.dao.DetailDAO;
import mall.dao.OrderDAO;
import mall.dao.ProductDAO;
import mall.entity.Detail;

import java.sql.SQLException;

public class DetailService {
    public static boolean isDetailValid(Detail detail) throws SQLException {
        return detail.getQuantity() > 0 && OrderDAO.select(detail.getOrderid()) != null && ProductDAO.select(detail.getProductid()) != null;
    }

    public static boolean createDetail(Detail detail) throws SQLException {
        return isDetailValid(detail) && DetailDAO.insertDetail(detail);
    }

    public static boolean addQuantity(Detail detail, Integer addQuantity) throws SQLException {
        detail.setQuantity(detail.getQuantity() + addQuantity);
        return isDetailValid(detail) && DetailDAO.updateDetail(detail);
    }
}
