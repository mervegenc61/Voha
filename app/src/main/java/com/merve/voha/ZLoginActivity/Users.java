package com.merve.voha.ZLoginActivity;



public class Users
{
    private String userId;
    private String ad;
    private String email;



    public Users() {
    }

    public Users(String userId, String ad, String email) {
        this.userId = userId;
        this.ad = ad;
        this.email = email;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }



    public String getAd() {
        return ad;
    }





}