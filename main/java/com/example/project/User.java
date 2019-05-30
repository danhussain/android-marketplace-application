package com.example.project;

class User {
    long userID;
    String firstName;
    String lastName;
    String email;
    String password;

    public User(long uID, String fName, String lName, String e, String p) {
        this.userID = uID;
        this.firstName = fName;
        this.lastName = lName;
        this.email = e;
        this.password = p;
    }
}
