package it.unisalento.se.models;

public class FCMTokenRegistration {

    private UserModel model;
    private String token;

    public UserModel getModel() {
        return model;
    }

    public void setModel(UserModel model) {
        this.model = model;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
