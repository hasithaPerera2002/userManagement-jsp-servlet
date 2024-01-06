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
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controller.orderController", value = "/orderController")
public class orderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String itemId = req.getParameter("selectedItemId");
        String quantity = req.getParameter("quantity");
        String total = req.getParameter("total");



            try {
                Connection connection = DbUtil.getInstance().getconnection();


                String sql = "INSERT INTO orders ( orderDate,totalAmount,quantity,itemId,status) VALUES ( ?,?,?,?,?)";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, LocalDate.now().toString());
                statement.setString(2, total);
                statement.setString(3, quantity);
                statement.setString(4, itemId);
                statement.setString(5, "Done");




                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    String sqlUpdateQuantity = "UPDATE item SET quantity = quantity - ? WHERE id = ?";
                    PreparedStatement statementUpdate = connection.prepareStatement(sqlUpdateQuantity);
                    statementUpdate.setInt(1, Integer.parseInt(quantity));
                    statementUpdate.setInt(2, Integer.parseInt(itemId));

                    int rowsUpdated = statementUpdate.executeUpdate();

                    if (rowsUpdated > 0) {
                        System.out.println("Item quantity updated successfully!");
                    } else {
                        System.out.println("Failed to update item quantity!");
                    }

                    resp.sendRedirect("orderPage.jsp");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        List<Item> itemList = getItems();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(itemList);
        PrintWriter out = resp.getWriter();
        out.println(jsonList);
        out.close();
    }

    public List<Item> getItems() {
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
}
