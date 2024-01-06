package controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controller.userPageController", value = "/userPageController")
public class userPageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("order".equals(action)) {
            resp.sendRedirect("orderPage.jsp");
        } else if ("inventory".equals(action)) {
            resp.sendRedirect("userItemManagement.jsp");
        } else if ("orders".equals(action)) {
            resp.sendRedirect("orders.jsp");
        }
    }
}
