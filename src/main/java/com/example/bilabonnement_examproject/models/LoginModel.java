package com.example.bilabonnement_examproject.models;

public class LoginModel {
    private int id;
    private String username;
    private String password;
    private int departmentId;

    public LoginModel(int id, String username, String password, int departmentId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "LoginModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
