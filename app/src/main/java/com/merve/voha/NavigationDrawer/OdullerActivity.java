package com.merve.voha.NavigationDrawer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.merve.voha.R;

public class OdullerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oduller);
        android.support.v7.widget.Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.oduller);
        toolbar.setTitleTextColor(getResources().getColor(R.color.beyaz));
        setSupportActionBar(toolbar);
    }
}
