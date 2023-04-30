package com.example.experimentalreverb;

public class Accesstoken{
    private static Accesstoken instance;
    private String token;

    private Accesstoken() {
        token = "default value";
    }

    public static synchronized Accesstoken getInstance() {
        if (instance == null) {
            instance = new Accesstoken();
        }
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String newValue) {
        token = newValue;
    }
}
