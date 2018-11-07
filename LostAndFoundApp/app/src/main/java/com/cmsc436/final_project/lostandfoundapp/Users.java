package com.cmsc436.final_project.lostandfoundapp;

public class Users {
    String id;
    String email;
    String password;
    int rating;
    String username;
    public Users(String email, int rating, String username){
        this.email = email;
        this.rating = rating;
        this.username = username;

    }
}
