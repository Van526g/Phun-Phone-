package com.example.quizapp;

public class User {

    String UserId;
    String UserName;
    int UserScore;
//User default constructor
    public User(){

    }
//User non-default constructor
    public User(String userId, String userName, int userScore) {
        this.UserId = userId;
        this.UserName = userName;
        this.UserScore = userScore;
    }
//GETTERS for User class
    public String getUserId() {
        return UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public int getUserScore() {
        return UserScore;
    }
}

