package mall.servlet.ProductServlet;

import mall.dao.ProductDAO;
import mall.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/productDetail")
public class ProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //coming in params "idproduct"@productList.jsp (or @showCart.jsp) & session attribute (User)"currentUser"@/userLogin
        Integer idproduct = Integer.parseInt(req.getParameter("idproduct"));
        Product product = null;
        try {
            product = ProductDAO.select(idproduct);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (product != null) {
            req.setAttribute("product", product);
            req.getRequestDispatcher("page/productShow.jsp").forward(req, resp);
        } else {
            PrintWriter out = resp.getWriter();
            out.write("<script>");
            out.write("alert('抱歉，查询商品时出现错误');");
            out.write("location.href='/catalogSelect?idcatalog=1';");
            out.write("</script>");
            out.close();
        }
    }
}
