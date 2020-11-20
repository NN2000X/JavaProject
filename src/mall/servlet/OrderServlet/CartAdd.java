package mall.servlet.OrderServlet;

import mall.dao.DetailDAO;
import mall.dao.OrderDAO;
import mall.entity.Detail;
import mall.entity.Order;
import mall.entity.User;
import mall.service.DetailService;
import mall.service.OrderService;
import mall.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/cartAdd")
public class CartAdd extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("currentUser");
        try {
            // 创建或get购物车
            Order cart = OrderService.createCart(user.getIduser());
            Integer productid = Integer.parseInt(req.getParameter("productid"));
            Integer quantity = Integer.parseInt(req.getParameter("quantity"));
            if (cart == null) {
                PrintWriter out = resp.getWriter();
                out.write("<script>");
                out.write("alert('抱歉，购物车创建失败');");
                out.write("location.href='/catalogSelect?idcatalog=1';");
                out.write("</script>");
                out.close();
            }
            // 查看购物车中是否已经有该商品
            Detail existedDetail = DetailDAO.selectDetailByProduct(cart.getIdorder(), productid);
            if (!ProductService.reduceStock(productid, quantity)) {
                PrintWriter out = resp.getWriter();
                out.write("<script>");
                out.write("alert('抱歉，库存不足');");
                out.write("location.href='/catalogSelect?idcatalog=1';");
                out.write("</script>");
                out.close();
            } else if (existedDetail == null)
                // 购物车中没有商品就创建detail，有的话就增加数量
                DetailService.createDetail(new Detail(null, cart.getIdorder(), productid, quantity));
            else if (!DetailService.addQuantity(existedDetail, quantity)) {
                PrintWriter out = resp.getWriter();
                out.write("<script>");
                out.write("alert('加购失败');");
                out.write("location.href='/catalogSelect?idcatalog=1';");
                out.write("</script>");
                out.close();
            }
            resp.sendRedirect("/catalogSelect?idcatalog=1");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
