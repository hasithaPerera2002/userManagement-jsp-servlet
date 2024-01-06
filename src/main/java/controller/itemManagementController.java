package controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import model.Item;
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

@WebServlet(name = "controller.itemManagementController", value = "/itemManagementController")
public class itemManagementController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        String quantity = req.getParameter("quantity");
        String supplierId = req.getParameter("supplierId");

        if (id == null) {
            try {
                Connection connection = DbUtil.getInstance().getconnection();
                PreparedStatement preparedStatement = connection.prepareStatement("insert into item(name,price,quantity,supplier_id) values(?,?,?,?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, price);
                preparedStatement.setString(3, quantity);
                preparedStatement.setString(4, supplierId);
                preparedStatement.executeUpdate();
                resp.sendRedirect("adminItemManagement.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            try {
                Connection connection = DbUtil.getInstance().getconnection();
                PreparedStatement preparedStatement = connection.prepareStatement("update item set name=?,price=?,quantity=? where id=?");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, price);
                preparedStatement.setString(3, quantity);
                preparedStatement.setString(4, id);
                preparedStatement.executeUpdate();
                resp.sendRedirect("adminItemManagement.jsp");
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
            PreparedStatement preparedStatement = connection.prepareStatement("delete from item where id=?");
            preparedStatement.setString(1, id);
            int executeUpdate = preparedStatement.executeUpdate();

            if (executeUpdate>0){
                System.out.println("Item with id "+id+" deleted successfully");
                resp.setStatus(200);
            }
            resp.sendRedirect("adminItemManagement.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<Item> items = getItems();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(items);
        PrintWriter out = resp.getWriter();
        out.println(jsonList);
        out.close();
    }

    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        try {
            Connection connection = DbUtil.getInstance().getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from item");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("quantity"), resultSet.getDouble("price"), resultSet.getInt("supplier_id"));

                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}
