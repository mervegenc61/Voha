package com.merve.voha.Model;

/**
 * Created by Merve on 26.04.2018.
 */

public class Score {
   private String id;
   private String userId;
   private String userName;
   private String bolumId;
   private int gold;
   private int diamond;
   private int totalbolum;
   private int globaltoplam;

    public Score() {
    }

    public Score(String id, String userId,String userName, String bolumId, int gold, int diamond, int totalbolum, int globaltoplam) {
        this.id = id;
        this.userId = userId;
        this.userName=userName;
        this.bolumId = bolumId;
        this.gold = gold;
        this.diamond = diamond;
        this.totalbolum = totalbolum;
        this.globaltoplam = globaltoplam;
    }

    public Score(String id, String userId,String userName, String bolumId, int gold, int diamond, int totalbolum) {
        this.id = id;
        this.userId = userId;
        this.userName=userName;
        this.bolumId = bolumId;
        this.gold = gold;
        this.diamond = diamond;
        this.totalbolum = totalbolum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBolumId() {
        return bolumId;
    }

    public void setBolumId(String bolumId) {
        this.bolumId = bolumId;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getDiamond() {
        return diamond;
    }

    public void setDiamond(int diamond) {
        this.diamond = diamond;
    }

    public int getTotalbolum() {
        return totalbolum;
    }

    public void setTotalbolum(int totalbolum) {
        this.totalbolum = totalbolum;
    }

    public int getGlobaltoplam() {
        return globaltoplam;
    }

    public void setGlobaltoplam(int globaltoplam) {
        this.globaltoplam = globaltoplam;
    }
}
