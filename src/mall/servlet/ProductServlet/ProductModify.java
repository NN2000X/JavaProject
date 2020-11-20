package mall.servlet.ProductServlet;

import mall.dao.CatalogDAO;
import mall.dao.ProductDAO;
import mall.entity.Product;
import mall.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/productModify")
public class ProductModify extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        try {
            String action = req.getParameter("action");
            String catalogIndex = "/catalogModify?idcatalog=1&action=show";
            if (action.equals("product")) {
                Product product = new Product(
                        null,
                        req.getParameter("name"),
                        Float.parseFloat(req.getParameter("price")),
                        Integer.parseInt(req.getParameter("stock")),
                        Integer.parseInt(req.getParameter("catalogid")),
                        req.getParameter("description"));
                if (!ProductDAO.insertProduct(product)) {
                    PrintWriter out = resp.getWriter();
                    out.write("<script>");
                    out.write("alert('抱歉，新建商品失败');");
                    out.write("location.href='/catalogModify?idcatalog=1&action=show';");
                    out.write("</script>");
                    out.close();
                } else
                    resp.sendRedirect("/catalogModify?idcatalog=" + product.getCatalogid().toString() + "&action=show");
            } else if (action.equals("name")) {
                Product product = ProductDAO.select(Integer.parseInt(req.getParameter("idproduct")));
                product.setName(req.getParameter("newName"));
                if (!ProductDAO.updateProduct(product)) {
                    PrintWriter out = resp.getWriter();
                    out.write("<script>");
                    out.write("alert('商品修改失败，请检查输入的内容');");
                    out.write("location.href='/catalogModify?idcatalog=1&action=show';");
                    out.write("</script>");
                    out.close();
                } else
                    resp.sendRedirect("/catalogModify?idcatalog="+product.getCatalogid()+"&action=show");
            } else if (action.equals("price")) {
                Product product = ProductDAO.select(Integer.parseInt(req.getParameter("idproduct")));
                product.setPrice(Float.parseFloat(req.getParameter("newPrice")));
                if (!ProductDAO.updateProduct(product)) {
                    PrintWriter out = resp.getWriter();
                    out.write("<script>");
                    out.write("alert('商品修改失败，请检查输入的内容');");
                    out.write("location.href='/catalogModify?idcatalog=1&action=show';");
                    out.write("</script>");
                    out.close();
                } else
                    resp.sendRedirect("/catalogModify?idcatalog="+product.getCatalogid()+"&action=show");
            } else if (action.equals("stock")) {
                Product product = ProductDAO.select(Integer.parseInt(req.getParameter("idproduct")));
                product.setStock(Integer.parseInt(req.getParameter("newStock")));
                if (!ProductDAO.updateProduct(product)) {
                    PrintWriter out = resp.getWriter();
                    out.write("<script>");
                    out.write("alert('商品修改失败，请检查输入的内容');");
                    out.write("location.href='/catalogModify?idcatalog=1&action=show';");
                    out.write("</script>");
                    out.close();
                } else
                    resp.sendRedirect("/catalogModify?idcatalog="+product.getCatalogid()+"&action=show");
            } else if (action.equals("catalog")) {
                Product product = ProductDAO.select(Integer.parseInt(req.getParameter("idproduct")));
                Integer catalogid = CatalogDAO.selectIdcatalogByName(req.getParameter("newCatalogName"));
                if (catalogid == null) {
                    PrintWriter out = resp.getWriter();
                    out.write("<script>");
                    out.write("alert('您输入的分类不存在哦，请先创建分类');");
                    out.write("location.href='page/productModify.jsp?idproduct=" + product.getIdproduct().toString() + "&action=modify';");
                    out.write("</script>");
                    out.close();
                } else if (!CatalogService.isEndCatalog(catalogid)) {
                    PrintWriter out = resp.getWriter();
                    out.write("<script>");
                    out.write("alert('您输入的分类不是最低类哦，无法在该类中创建');");
                    out.write("location.href='page/productModify.jsp?idproduct=" + product.getIdproduct().toString() + "&action=modify';");
                    out.write("</script>");
                    out.close();
                } else {
                    product.setCatalogid(catalogid);
                    if (!ProductDAO.updateProduct(product)) {
                        PrintWriter out = resp.getWriter();
                        out.write("<script>");
                        out.write("alert('商品修改失败，请检查输入的内容');");
                        out.write("location.href='/catalogModify?idcatalog=1&action=show';");
                        out.write("</script>");
                        out.close();
                    } else
                        resp.sendRedirect("/catalogModify?idcatalog="+product.getCatalogid()+"&action=show");
                }
            } else if (action.equals("description")) {
                Product product = ProductDAO.select(Integer.parseInt(req.getParameter("idproduct")));
                product.setDescription(req.getParameter("newDescription"));
                if (!ProductDAO.updateProduct(product)) {
                    PrintWriter out = resp.getWriter();
                    out.write("<script>");
                    out.write("alert('商品修改失败，请检查输入的内容');");
                    out.write("location.href='/catalogModify?idcatalog=1&action=show';");
                    out.write("</script>");
                    out.close();
                } else
                    resp.sendRedirect("/catalogModify?idcatalog="+product.getCatalogid()+"&action=show");
            } else if (action.equals("delete")) {
                if (!ProductDAO.deleteProduct(Integer.parseInt(req.getParameter("idproduct")))) {
                    PrintWriter out = resp.getWriter();
                    out.write("<script>");
                    out.write("alert('抱歉，商品删除失败');");
                    out.write("location.href='/catalogModify?idcatalog=1&action=show';");
                    out.write("</script>");
                    out.close();
                } else
                    resp.sendRedirect(catalogIndex);
            } else {
                PrintWriter out = resp.getWriter();
                out.write("<script>");
                out.write("alert('抱歉，操作失败');");
                out.write("location.href='/catalogModify?idcatalog=1&action=show';");
                out.write("</script>");
                out.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
