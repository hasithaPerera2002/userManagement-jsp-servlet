package model;



import enums.UserType;

import java.sql.SQLException;

public class User {

    public User() throws SQLException {
    }
    public User(int id, String name, String email, String age, String address, UserType userType,String phone) throws SQLException {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
        this.userType = userType;
        this.phone=phone;

    }

    private int id;

    private String name;
    private String email;
    private String age;

    private String address;

    private UserType userType;

    private String phone;


    public int getId() {
        return id;
    }

    public void setId(int id) {
    	this.id=id;
    }
    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
    	this.phone=phone;
    }





}
