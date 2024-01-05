package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "controller.adminPageController", value = "/adminPageController")
public class adminPageController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("action"));
        String action = req.getParameter("action");
        if ("user".equals(action)) {
            resp.sendRedirect("userManagement.jsp");
        } else if ("item".equals(action)) {
            resp.sendRedirect("adminItemManagement.jsp");
        } else if ("supplier".equals(action)) {
            resp.sendRedirect("supplierManagement.jsp");
        }
    }
}
