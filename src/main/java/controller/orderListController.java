package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebServlet(name = "controller.OrderListController", value = "/orders")
public class orderListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<Order> orderList = getOrders();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(orderList);
        PrintWriter out = resp.getWriter();
        out.println(jsonList);
        out.close();
    }

    public List<Order> getOrders() {
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
