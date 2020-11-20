package mall.servlet.CatalogServlet;

import mall.dao.CatalogDAO;
import mall.entity.Catalog;
import mall.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/catalogSelect")
public class CatalogSelect extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        //coming in param "idcatalog"@userMenu.jsp & session attribute (User)"currentUser"@/userLogin
        try {
            Catalog currentCatalog = CatalogDAO.select(Integer.parseInt(req.getParameter("idcatalog")));
            //如果是最末尾的分类就展示商品，不是就进入更细分的分类
            if (CatalogService.isEndCatalog(currentCatalog.getIdcatalog())) {
                req.setAttribute("idcatalog", currentCatalog.getIdcatalog());
                req.getRequestDispatcher("/productList").forward(req, resp);
            } else {
                ArrayList<Catalog> catalogs = CatalogDAO.selectByParentid(currentCatalog.getIdcatalog());
                if (catalogs != null) {
                    req.setAttribute("currentCatalog", currentCatalog);
                    req.setAttribute("catalogs", catalogs);
                    req.getRequestDispatcher("page/catalogSelect.jsp").forward(req, resp);
                } else {
                    PrintWriter out = resp.getWriter();
                    out.write("<script>");
                    out.write("alert('抱歉，查询商品分类时发生错误');");
                    out.write("location.href='page/userMenu.jsp';");
                    out.write("</script>");
                    out.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
