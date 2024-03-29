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

@WebServlet(name = "controller.itemController", value = "/itemController")
public class itemController extends HttpServlet {

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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String quantity = req.getParameter("quantity");
        try {
            Connection connection = DbUtil.getInstance().getconnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update item set quantity=? where id=?");

            preparedStatement.setString(1, quantity);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
            resp.sendRedirect("userItemManagement.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
