package mall.servlet.ProductServlet;

import mall.dao.CatalogDAO;
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
import java.util.ArrayList;

@WebServlet("/productList")
public class ProductList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //coming in params "idcatalog"@/catalogSelect & session attribute "currentUser"@/userLogin
        Integer currentCatalog = Integer.parseInt(req.getParameter("idcatalog"));
        ArrayList<Product> products = null;
        try {
            products = ProductDAO.selectByCatalogid(currentCatalog);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (products != null) {
            req.setAttribute("products", products);
            req.getRequestDispatcher("page/productList.jsp").forward(req, resp);
        } else {
            PrintWriter out = resp.getWriter();
            out.write("<script>");
            out.write("alert('抱歉，该分类下暂时没有商品');");
            out.write("location.href='/catalogSelect?idcatalog=1';");
            out.write("</script>");
            out.close();
        }
    }
}
