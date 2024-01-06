package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "controller.loginController", value = "/loginController")
public class loginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        if (userName.equals("user") && password.equals("123")) {
            resp.sendRedirect("userPage.jsp");
        } else if (userName.equals("admin") && password.equals("admin")) {
            resp.sendRedirect("adminPage.jsp");
        } else {
            resp.setContentType("text/html");
            PrintWriter out = resp.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Invalid username or password');");
            out.println("location='index.jsp';");
            out.println("</script>");
        }
    }
}
