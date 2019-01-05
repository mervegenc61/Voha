package com.merve.voha.ZWelcomeActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.merve.voha.NavigationDrawer.NavigationActivity;
import com.merve.voha.R;
import com.merve.voha.ZLoginActivity.LoginMainActivity;

public class OneScreenWelcomeActivity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    Button button;
    LinearLayout ll1,ll2,ll3,ll4;
    private PrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_activity_one_screen_welcome);
        setup();
        lottieAnimasyon("coding_ape.json");
    }
    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        if(FirebaseAuth.getInstance().getCurrentUser()!=null) {
            startActivity(new Intent(OneScreenWelcomeActivity.this, NavigationActivity.class));
        }
        else{
            startActivity(new Intent(OneScreenWelcomeActivity.this, LoginMainActivity.class));
        }
    }
    private void lottieAnimasyon(String animasyonCesidi){
        lottieAnimationView.setAnimation(animasyonCesidi);
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();
    }
    private void setup(){
        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        lottieAnimationView=findViewById(R.id.lottie_stars);
        ll1=findViewById(R.id.ll1);
        ll2=findViewById(R.id.ll2);
        ll3=findViewById(R.id.ll3);
        ll4=findViewById(R.id.ll4);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchHomeScreen();
            }
        });
    }
}
