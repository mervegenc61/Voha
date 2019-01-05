package com.merve.voha.Model;

/**
 * Created by Merve on 30.04.2018.
 */

public class ToplamScore {
    private String userid;
    private String username;
    private int toplamscore;


    public ToplamScore(String userid,String username, int toplamscore) {
        this.userid = userid;
        this.toplamscore = toplamscore;
        this.username=username;
    }
    public String getUserid() {
        return userid;
    }

    public int getToplamscore() {
        return toplamscore;
    }
    public ToplamScore() {
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setToplamscore(int toplamscore) {
        this.toplamscore = toplamscore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
