package com.merve.voha;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Merve on 11.08.2018.
 */

public class ClassAdmob {
    private static final String reklamID = "ca-app-pub-6072892436865731/1628686721";
    public static InterstitialAd getInstance(Context context) {
        InterstitialAd mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(reklamID);
        return mInterstitialAd;
    }
    public static AdRequest getAdRequest() {
        return new AdRequest.Builder()
                .addTestDevice("**********")
                .build();
    }
}
