package com.example.finalexam.model;

/**
 * Created by computer on 18/12/2559.
 */

public class Member {

    private String mName;
    private String mUsername;
    private String mPassword;

    public Member(String mName, String mUsername, String mPassword) {
        this.mName = mName;
        this.mUsername = mUsername;
        this.mPassword = mPassword;
    }

    public String getName() {
        return mName;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getPassword() {
        return mPassword;
    }
}
