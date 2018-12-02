package com.cmsc436.final_project.lostandfoundapp;


public class Users {
    String id;
    String email;
    String password;
    int rating;
    String username;
    String imageURL;


    public Users(String email, int rating, String username,String imageURL){
        this.email = email;
        this.rating = rating;
        this.username = username;
        this.imageURL = imageURL;
    }
    public Users()
    {}

    public String getEmail() {
        return email;
    }
    public String getImageURL() {
        return imageURL;
    }
    public String getUsername() {
        return username;
    }
    public int getRating(){
        return  rating;
    }
}
