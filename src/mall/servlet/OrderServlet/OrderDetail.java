package mall.servlet.OrderServlet;

import mall.dao.DetailDAO;
import mall.dao.OrderDAO;
import mall.dao.ProductDAO;
import mall.dao.UserDAO;
import mall.entity.Detail;
import mall.entity.Order;
import mall.entity.Product;
import mall.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/orderDetail")
public class OrderDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Order order = OrderDAO.select(Integer.parseInt(req.getParameter("idorder")));
            User buyer = UserDAO.selectByIduser(order.getUserid());
            ArrayList<Detail> details = DetailDAO.selectByOrderid(order.getIdorder());
            ArrayList<Product> products = new ArrayList<>();
            for (int i = 0; i < details.size(); i++)
                products.add(ProductDAO.select(details.get(i).getProductid()));

            req.setAttribute("order", order);
            req.setAttribute("buyer", buyer);
            req.setAttribute("details", details);
            req.setAttribute("products",products);

            req.getRequestDispatcher("page/orderDetail.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
