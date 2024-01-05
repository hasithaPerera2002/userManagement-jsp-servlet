package model;



import enums.Gender;
import enums.UserType;

import java.sql.Connection;
import java.sql.SQLException;

public class User {

    public User() throws SQLException {
    }
    public User(String name, String email, String age, String address, UserType userType, Gender gender) throws SQLException {
        this.name = name;
        this.email = email;
        this.age = age;
        this.address = address;
        this.userType = userType;
        this.gender = gender;
    }

    private String name;
    private String email;
    private String age;

    private String address;

    private UserType userType;

    private Gender gender;

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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }



}
