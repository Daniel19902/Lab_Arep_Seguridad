package edu.escuelaing.arem.model;

public class User {

    private String userName;
    private String password;
    private String token;
    private String frace = "hola que hace";


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getFrace() {
        return frace;
    }

    public void setFrace(String frace) {
        this.frace = frace;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
