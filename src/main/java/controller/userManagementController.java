package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import enums.UserType;
import model.User;
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

@WebServlet(name = "controller.userManagementController", value = "/userManagementController")
public class userManagementController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");


        List<User> userList = getUsers();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonList = objectMapper.writeValueAsString(userList);

        PrintWriter out = resp.getWriter();
        out.println(jsonList);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String age = req.getParameter("age");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");


       if(id==null) {
            try {
                Connection connection = DbUtil.getInstance().getconnection();


                String sql = "INSERT INTO users (name, email,age,address,userType,phone) VALUES ( ?,?,?,?,?,?)";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, age);
                statement.setString(4, address);
                statement.setString(5, "STAFF");
                statement.setString(6, phone);


                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    resp.sendRedirect("userManagement.jsp");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();

            }
        }else {
            try {
                Connection connection = DbUtil.getInstance().getconnection();

                String sql = "UPDATE users SET name=?, email=?, age=?, address=?, phone=? WHERE id=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, age);
                statement.setString(4, address);
                statement.setString(5, phone);
                statement.setString(6, id);
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    resp.sendRedirect("userManagement.jsp");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
       }
    }

    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = DbUtil.getInstance().getconnection()) {
            String sql = "SELECT * FROM users";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String age = resultSet.getString("age");
                String address = resultSet.getString("address");

                String phone = resultSet.getString("phone");


                User user = new User(id, name, email, age, address, UserType.USER, phone);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        try (Connection connection = DbUtil.getInstance().getconnection()) {
            String sql = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userId);
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    resp.sendRedirect("userManagement.jsp");
                } else {
                    System.out.println("User with ID " + userId + " not found");

                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


}
