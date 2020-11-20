package mall.servlet.CatalogServlet;

import mall.dao.CatalogDAO;
import mall.dao.ProductDAO;
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

@WebServlet("/catalogModify")
public class CatalogModify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        // 传进来的参数有idcatalog和action两个，如果从catalogModify.jsp传进则还有newName参数
        try {
            Integer idcatalog = Integer.parseInt(req.getParameter("idcatalog"));
            Catalog currentCatalog = CatalogDAO.select(idcatalog);
            String action = req.getParameter("action");
            if (action.equals("show")) {
                if (CatalogService.isEmptyCatalog(idcatalog)) {
                    req.setAttribute("currentCatalog", currentCatalog);
                    req.getRequestDispatcher("page/catalogModify.jsp").forward(req, resp);
                } else if (CatalogService.isEndCatalog(idcatalog)) {
                    req.setAttribute("currentCatalog", currentCatalog);
                    req.setAttribute("products", ProductDAO.selectByCatalogid(idcatalog));
                    req.getRequestDispatcher("page/catalogModify.jsp").forward(req, resp);
                } else {
                    req.setAttribute("currentCatalog", currentCatalog);
                    req.setAttribute("catalogs", CatalogDAO.selectByParentid(idcatalog));
                    req.getRequestDispatcher("page/catalogModify.jsp").forward(req, resp);
                }
            }
            // 增加分类
            else if (action.equals("insert")) {
                String newName = req.getParameter("newName");
                if (!CatalogService.addCatalog(newName, idcatalog)) {
                    PrintWriter out = resp.getWriter();
                    out.write("<script>");
                    out.write("alert('抱歉，创建新的分类时发生错误');");
                    out.write("location.href='/catalogModify?idcatalog=" + idcatalog.toString() + "&action=show';");
                    out.write("</script>");
                    out.close();
                } else
                    // 转到增加后的当前分类，查看效果
                    resp.sendRedirect("/catalogModify?idcatalog=" + idcatalog.toString() + "&action=show");
            } else if (action.equals("update")) {
                String newName = req.getParameter("newName");
                if (!CatalogService.modifyName(idcatalog, newName)) {
                    PrintWriter out = resp.getWriter();
                    out.write("<script>");
                    out.write("alert('抱歉，修改类名时发生错误');");
                    out.write("location.href='/catalogModify?idcatalog=" + idcatalog.toString() + "&action=show';");
                    out.write("</script>");
                    out.close();
                } else
                    resp.sendRedirect("/catalogModify?idcatalog=" + idcatalog.toString() + "&action=show");
            } else if (action.equals("delete")) {
                Integer parentid = CatalogDAO.selectParentid(idcatalog);
                if (!CatalogService.removeCatalog(idcatalog)) {
                    PrintWriter out = resp.getWriter();
                    out.write("<script>");
                    out.write("alert('该分类下有已被购买的商品，请将它们移出后，再删除此分类');");
                    out.write("</script>");
                    out.close();
                } else
                    resp.sendRedirect("/catalogModify?idcatalog=" + parentid.toString() + "&action=show");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
