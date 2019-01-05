package com.merve.voha.NavigationDrawer;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.merve.voha.ClassAdmob;
import com.merve.voha.Model.ToplamScore;
import com.merve.voha.R;

public class BasariSiralamasi extends AppCompatActivity {
    RecyclerView rv_score;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    LottieAnimationView lottieAnimationView;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_recylerview_score);
        android.support.v7.widget.Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.skorTablosu);
        toolbar.setTitleTextColor(getResources().getColor(R.color.beyaz));
        setSupportActionBar(toolbar);
        rv_score =findViewById(R.id.rv_score);
        lottieAnimationView=findViewById(R.id.lottie_score);
        relativeLayout=findViewById(R.id.relative);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        //lottieAnimasyon("coin.json");
        rv_score.setLayoutManager(layoutManager);
        rv_score.setHasFixedSize(true);
        rv_score.setItemAnimator(new DefaultItemAnimator());
        if(networkConnection()) {
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("toplam");
            Query queryRef = databaseReference.orderByChild("toplamscore").limitToLast(10);
            final FirebaseRecyclerAdapter<ToplamScore, ScoreViewHolder> adapter = new FirebaseRecyclerAdapter<ToplamScore, ScoreViewHolder>(
                    ToplamScore.class,
                    R.layout.z_cardview_score,
                    ScoreViewHolder.class,
                    queryRef
            ) {
                @Override
                public void populateViewHolder(final ScoreViewHolder viewHolder, final ToplamScore model, final int position) {
                    ToplamScore ts = getItem(position);
                    viewHolder.setScoreBilgi(ts.getToplamscore());
                    viewHolder.setUsername(model.getUsername());
                }
            };
            rv_score.setAdapter(adapter);
        }
        else{
            setContentView(R.layout.lottie_internet_anim);
            Toast.makeText(this, R.string.internetBagKontEt, Toast.LENGTH_LONG).show();
            if(networkConnection()){
               startActivity(new Intent(BasariSiralamasi.this,BasariSiralamasi.class));
               finish();
            }
        }
        final InterstitialAd mInterstitialAd = ClassAdmob.getInstance(getApplicationContext());
        AdRequest adRequest = ClassAdmob.getAdRequest();
        if (mInterstitialAd != null) {
            mInterstitialAd.loadAd(adRequest);
        }
        assert mInterstitialAd != null;
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                if (mInterstitialAd.isLoaded()) {
                    //reklam yüklenmişse gösteriliyor
                    mInterstitialAd.show();
                }
                Log.i("Ads", "onAdLoaded");
            }
            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
            }
            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.i("Ads", "onAdOpened");
            }
            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }
            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Log.i("Ads", "onAdClosed");
            }
        });
    }
            public static class ScoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
                private TextView userName, scoreBilgi;
                LottieAnimationView lottieAnimationView,lottieAnimationView2;
                View mView;
                public ScoreViewHolder(View itemView) {
                    super(itemView);
                    mView = itemView;
                    userName = itemView.findViewById(R.id.tv_username);
                    scoreBilgi = itemView.findViewById(R.id.tv_scorebilgisi);
                    lottieAnimationView=itemView.findViewById(R.id.lottie_score);
                    lottieAnimationView2=itemView.findViewById(R.id.cv_kullaniciResmi);
                    lottieAnimationView.setAnimation("star.json");
                    lottieAnimationView.loop(true);
                    lottieAnimationView.playAnimation();
                    lottieAnimationView2.setAnimation("face.json");
                    lottieAnimationView2.loop(true);
                    lottieAnimationView2.playAnimation();
                    itemView.setOnClickListener(this);
                }
                private void setUsername(String username) {
                    userName.setText(username);
                }
                private void setScoreBilgi(int score) {
                  scoreBilgi.setText(String.valueOf(score));
                }
                @Override
                public void onClick(View view) {
                }
            }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    public boolean networkConnection() {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService (Context.CONNECTIVITY_SERVICE);
        if (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected()) {
            return true;
        }else{
            return false;
        }
    }

}
