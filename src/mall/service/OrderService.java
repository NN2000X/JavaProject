package mall.service;

import mall.dao.DetailDAO;
import mall.dao.OrderDAO;
import mall.dao.ProductDAO;
import mall.entity.Detail;
import mall.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderService {
    public static float getTotalPrice(Integer idorder) throws SQLException {
        float sum = 0;
        ArrayList<Detail> details = DetailDAO.selectByOrderid(idorder);
        for (int i = 0; i < details.size(); i++)
            sum += ProductDAO.select(details.get(i).getProductid()).getPrice() * details.get(i).getQuantity();
        return sum;
    }

    public static Order createCart(Integer iduser) throws SQLException {
        Order cart = OrderDAO.selectCartByUserid(iduser);
        if (cart == null) {
            OrderDAO.insertOrder(iduser);
            cart = OrderDAO.selectCartByUserid(iduser);
        }
        return cart;
    }

    public static boolean commitCart(Integer idorder, String email) throws SQLException {
        Order order = OrderDAO.select(idorder);
        order.setEmail(email);
        order.setCommit(1);
        return OrderDAO.updateOrder(order);
    }
}
