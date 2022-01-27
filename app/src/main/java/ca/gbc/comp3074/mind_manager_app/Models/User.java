package ca.gbc.comp3074.mind_manager_app.Models;

public class User {

    private int id;
    private String role;
    private String userName;
    private String firstName;
    private String password;

    public User(String role, String userName, String firstName, String password) {
        this.role = role;
        this.userName = userName;
        this.firstName = firstName;
        this.password = password;
    }

    public User(int id, String role, String userName, String firstName) {
        this.id = id;
        this.role = role;
        this.userName = userName;
        this.firstName = firstName;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "role = '" + role + '\'' +
                ", userName = '" + userName + '\'' +
                ", firstName = '" + firstName + '\'' +
                ", password = '" + password + '\'' +
                '}';
    }
}