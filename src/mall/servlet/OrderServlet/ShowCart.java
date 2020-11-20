package mall.servlet.OrderServlet;

import mall.dao.DetailDAO;
import mall.dao.OrderDAO;
import mall.dao.ProductDAO;
import mall.entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/showCart")
public class ShowCart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        User user = (User) req.getSession().getAttribute("currentUser");
        try {
            Order cart = OrderDAO.selectCartByUserid(user.getIduser());
            if (cart == null)
                req.getRequestDispatcher("page/showCart.jsp").forward(req, resp);
            ArrayList<Detail> details = DetailDAO.selectByOrderid(cart.getIdorder());
            ArrayList<ShortDetail> shortDetails = new ArrayList<>();
            Float totalPrice = new Float(0);
            for (int i = 0; i < details.size(); i++) {
                Product product = ProductDAO.select(details.get(i).getProductid());
                shortDetails.add(new ShortDetail(product.getIdproduct(), product.getName(), product.getPrice(), details.get(i).getQuantity()));
                totalPrice += shortDetails.get(shortDetails.size() - 1).getSubtotal();
            }
            req.setAttribute("shortDetails", shortDetails);
            req.setAttribute("idorder", cart.getIdorder());
            req.setAttribute("totalPrice", totalPrice);
            req.getRequestDispatcher("page/showCart.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
