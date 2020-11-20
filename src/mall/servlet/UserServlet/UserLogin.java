package mall.servlet.UserServlet;

import mall.entity.User;
import mall.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/userLogin")
public class UserLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        User user = new User(null, req.getParameter("username"), req.getParameter("password"));
        try {
            user = UserService.userLogin(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", user);
            if (user.getUsername().equals("admin"))
                resp.sendRedirect("page/adminMenu.jsp");
            else
                resp.sendRedirect("page/userMenu.jsp");
        } else {
            PrintWriter out = resp.getWriter();
            out.write("<script>");
            out.write("alert('抱歉，您输入的用户名或密码有误');");
            out.write("location.href='page/userLogin.jsp';");
            out.write("</script>");
            out.close();
        }
    }
}
