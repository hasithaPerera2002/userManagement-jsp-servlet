package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Item;
import model.Order;
import util.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controller.reportController", value = "/reportController")
public class reportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("inventoryStatus".equals(action)) {
            List<Item> itemList = generateInventoryStatusReport(req, resp);
            resp.setContentType("application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonList = objectMapper.writeValueAsString(itemList);
            PrintWriter out = resp.getWriter();
            out.println(jsonList);
            out.close();
        } else if ("salesReport".equals(action)) {
            List<Order> orderList = generateSalesReport(req, resp);
            resp.setContentType("application/json");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonList = objectMapper.writeValueAsString(orderList);
            PrintWriter out = resp.getWriter();
            out.println(jsonList);
            out.close();
        } else {
            resp.getWriter().println("Invalid action");
        }
    }

    private List<Item> generateInventoryStatusReport(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Item> itemsList = new ArrayList<>();
        try {
            Connection connection = DbUtil.getInstance().getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from item");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String supplierId = resultSet.getString("supplier_id");
                itemsList.add(new Item(id, name, quantity, price, Integer.parseInt(supplierId)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return itemsList;

    }


    private List<Order> generateSalesReport(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Order> orderList = new ArrayList<>();
        try {
            Connection connection = DbUtil.getInstance().getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date orderDate = resultSet.getDate("orderDate");
                int totalAmount = resultSet.getInt("totalAmount");
                String status = resultSet.getString("status");
                int quantity = resultSet.getInt("quantity");
                int itemId = resultSet.getInt("itemId");


                Order order = new Order(id, orderDate, totalAmount, status, quantity, itemId);
                orderList.add(order);
            }
            connection.close(); // Close the connection after use
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }


}
