package com.merve.voha.NavigationDrawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.merve.voha.R;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_activity_help);
        android.support.v7.widget.Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.yardim);
        toolbar.setTitleTextColor(getResources().getColor(R.color.beyaz));
        setSupportActionBar(toolbar);
        Button help= findViewById(R.id.help);
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Use when user trigger on  visit website
                String url = "https://www.facebook.com/?react=AQA_Oz4ylVpCwApn";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                Intent chooser = Intent.createChooser(intent, getResources().getString(R.string.birlikteAc));
                startActivity(chooser);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(HelpActivity.this,NavigationActivity.class));
    }
}
