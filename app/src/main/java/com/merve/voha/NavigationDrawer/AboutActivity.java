package com.merve.voha.NavigationDrawer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.merve.voha.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Button btn=findViewById(R.id.visit_website);
        android.support.v7.widget.Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.iletisimeGec));
        toolbar.setTitleTextColor(getResources().getColor(R.color.beyaz));
        setSupportActionBar(toolbar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://play.google.com/store/apps/details?id=com.merve.voha";
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
        startActivity(new Intent(AboutActivity.this,NavigationActivity.class));
    }
}
