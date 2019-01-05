package com.merve.voha.JavaEgitimleri;

public class CardItem {

    private int mTextResource;
    private int mTitleResource;
    private int resimm;
    private int button;

    public CardItem(int title, int text, int resim) {
        mTitleResource = title;
        mTextResource = text;
        resimm=resim;
    }

    public CardItem(int title, int text) {
        mTitleResource = title;
        mTextResource = text;

    }
    public int getButton() {
        return button;
    }

    public CardItem(int mTitleResource, int mTextResource, int resimm, int button) {
        this.mTitleResource = mTitleResource;
        this.mTextResource = mTextResource;
        this.resimm = resimm;
        this.button = button;
    }
    public CardItem(int text){
        mTextResource=text;
    }

    public int getResim() {
        return resimm;}

    public int getText() {
        return mTextResource;
    }

    public int getTitle() {
        return mTitleResource;
    }
}