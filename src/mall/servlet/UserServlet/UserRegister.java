package mall.servlet.UserServlet;

import mall.dao.UserDAO;
import mall.entity.User;
import mall.service.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/userRegister")
public class UserRegister extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        User user = new User(null, req.getParameter("username"), req.getParameter("password"));
        try {
            if (user.getUsername() == null || user.getUsername().isBlank() || UserService.isUsernameOccupied(req.getParameter("username"))) {
                PrintWriter out = resp.getWriter();
                out.write("<script>");
                out.write("alert('抱歉，此用户名不可用');");
                out.write("location.href='page/userRegister.jsp';");
                out.write("</script>");
                out.close();
            } else if (user.getPassword() == null || user.getPassword().isBlank() || user.getPassword().length() < 5) {
                PrintWriter out = resp.getWriter();
                out.write("<script>");
                out.write("alert('抱歉，此密码不符合规范');");
                out.write("location.href='page/userRegister.jsp';");
                out.write("</script>");
                out.close();
            } else if (!UserDAO.insertUser(user)) {
                PrintWriter out = resp.getWriter();
                out.write("<script>");
                out.write("alert('抱歉，注册失败');");
                out.write("location.href='page/userRegister.jsp';");
                out.write("</script>");
                out.close();
            } else
                resp.sendRedirect("page/userLogin.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
