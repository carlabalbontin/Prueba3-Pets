package com.cbalt.prueba03.models;

public class User {

    String name, email, key;

    public User() {
    }

    public User(String name, String email, String key) {
        this.name = name;
        this.email = email;
        this.key = key;
    }

    public String getName() {
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
