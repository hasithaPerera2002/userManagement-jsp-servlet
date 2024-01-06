package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Supplier;
import util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "controller.supplierController", value = "/supplierController")
public class supplierController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        List<Supplier> supplierList = getSuppliers();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(supplierList);
        PrintWriter out = resp.getWriter();
        out.println(jsonList);
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String category = req.getParameter("category");
        String quantity = req.getParameter("quantity");
        String price = req.getParameter("price");

        if (id == null) {
            try {
                Connection connection = DbUtil.getInstance().getconnection();
                PreparedStatement preparedStatement = connection.prepareStatement("insert into supplier(name,email,category,quantity,price) values(?,?,?,?,?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, category);
                preparedStatement.setString(4, quantity);
                preparedStatement.setString(5, price);
                preparedStatement.executeUpdate();
                resp.sendRedirect("supplierManagement.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            try {
                Connection connection = DbUtil.getInstance().getconnection();
                PreparedStatement preparedStatement = connection.prepareStatement("update supplier set name=?,email=?,category=?,quantity=?,price=? where id=?");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, category);
                preparedStatement.setString(4, quantity);
                preparedStatement.setString(5, price);
                preparedStatement.setString(6, id);
                preparedStatement.executeUpdate();
                resp.sendRedirect("supplierManagement.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            Connection connection = DbUtil.getInstance().getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from supplier where id=?");
            preparedStatement.setString(1, id);
            int executeUpdate = preparedStatement.executeUpdate();

            if (executeUpdate > 0) {
                resp.setStatus(200);
            }

            resp.sendRedirect("supplierManagement.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Supplier> getSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            Connection connection = DbUtil.getInstance().getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM supplier");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String category = resultSet.getString("category"); // Assuming category is a String
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price"); // Assuming price is a numeric field

                Supplier supplier = new Supplier(id, name,  category, quantity, price,email);
                suppliers.add(supplier);
            }
            // Close the ResultSet, PreparedStatement, and Connection after usage
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

}
