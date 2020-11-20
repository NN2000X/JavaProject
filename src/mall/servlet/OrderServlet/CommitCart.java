package mall.servlet.OrderServlet;

import mall.service.OrderService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;

@WebServlet("/commitCart")
public class CommitCart extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");

        Integer idorder = Integer.parseInt(req.getParameter("idorder"));
        String email = req.getParameter("email");
        String totalPrice = req.getParameter("totalPrice");
        try {
            if (OrderService.commitCart(idorder, email)) {
                // 发送确认邮件
                String src = "mallbynn@163.com";
                // 此处password为163邮箱smtp授权码
                String src_pwd = "PDIVXWEXEPZWGXDR";
                String content = new String("这里是来自 Mall by NN 的订单确认邮件~\n您购买了总价为：");
                content += totalPrice + "的商品\n订单号为：" + idorder.toString() + "\n\n感谢您光临，祝好。";

                Properties p = System.getProperties();
                p.setProperty("mail.smtp.host", "smtp.163.com");
                p.setProperty("mail.smtp.auth", "true");
                MimeMessage msg = new MimeMessage(Session.getDefaultInstance(p, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(src, src_pwd);
                    }
                }));
                msg.setFrom(new InternetAddress(src));
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
                msg.setSubject("【Mall by NN】订单确认");
                msg.setText(content);
                Transport.send(msg);
                PrintWriter out = resp.getWriter();
                out.write("<script>");
                out.write("alert('已发送订单确认邮件，请查收');");
                out.write("location.href='/showCart';");
                out.write("</script>");
                out.close();
            } else {
                PrintWriter out = resp.getWriter();
                out.write("<script>");
                out.write("alert('订单提交失败，请检查您的邮件地址');");
                out.write("location.href='/showCart';");
                out.write("</script>");
                out.close();
            }
        } catch (SQLException | MessagingException e) {
            e.printStackTrace();
        }
    }
}