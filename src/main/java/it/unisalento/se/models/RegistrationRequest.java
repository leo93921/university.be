package it.unisalento.se.models;

public class RegistrationRequest extends UserModel {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
