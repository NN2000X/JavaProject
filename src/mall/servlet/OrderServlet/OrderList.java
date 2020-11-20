package mall.servlet.OrderServlet;

import mall.dao.OrderDAO;
import mall.dao.UserDAO;
import mall.entity.Order;
import mall.entity.User;
import mall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/orderList")
public class OrderList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ArrayList<Order> orders= OrderDAO.selectCommittedOrders();
            ArrayList<User> buyers=new ArrayList<>();
            ArrayList<Float> totalPrices=new ArrayList<>();
            for(int i=0;i<orders.size();i++) {
                buyers.add(UserDAO.selectByIduser(orders.get(i).getUserid()));
                totalPrices.add(OrderService.getTotalPrice(orders.get(i).getIdorder()));
            }
            req.setAttribute("orders",orders);
            req.setAttribute("buyers",buyers);
            req.setAttribute("totalPrices",totalPrices);
            req.getRequestDispatcher("page/orderList.jsp").forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
