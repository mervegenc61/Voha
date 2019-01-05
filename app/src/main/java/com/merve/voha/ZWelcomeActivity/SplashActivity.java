package com.merve.voha.ZWelcomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.merve.voha.NavigationDrawer.NavigationActivity;
import com.merve.voha.R;
import com.merve.voha.ZLoginActivity.LoginMainActivity;

public class SplashActivity extends AppCompatActivity {

    static int gosterim_suresi=3000;
    final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(firebaseAuth.getCurrentUser()!=null){
                    startActivity(new Intent(SplashActivity.this,NavigationActivity.class));
                }
                else{
                    Intent i = new Intent(SplashActivity.this, LoginMainActivity.class);
                    startActivity(i);
                    finish();}
            }
        },gosterim_suresi);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}