package com.butchjgo.linkservice.factory;

/**
 * Created by root on 5/14/2017.
 */
public abstract class Account {

    protected String email, password;

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Account{"
                + "email='" + email + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
