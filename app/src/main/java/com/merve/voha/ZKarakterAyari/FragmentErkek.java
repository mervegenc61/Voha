package com.merve.voha.ZKarakterAyari;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.merve.voha.NavigationDrawer.NavigationActivity;
import com.merve.voha.R;

/**
 * Created by Merve on 8.03.2018.
 */

public class FragmentErkek extends Fragment {
    public static FragmentErkek newInstance() {
        FragmentErkek fragment = new FragmentErkek();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.z_karakter_fragment_erkek, container, false);
        ImageView iv_istekliErkek=view.findViewById(R.id.istekliErkek);
        ImageView iv_gezginErkek=view.findViewById(R.id.gezginErkek);
        ImageView iv_calisanErkek=view.findViewById(R.id.calisanErkek);
        ImageView iv_ogrenciErkek=view.findViewById(R.id.ogrenciErkek);
        ImageView iv_romantikErkek=view.findViewById(R.id.romantikErkek);
        iv_istekliErkek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               karakterAyari(R.drawable.res_assets_profile_profilemaleenthusiast);
            }
        });
        iv_gezginErkek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           karakterAyari(R.drawable.res_assets_profile_profilemaletraveller);
            }
        });
        iv_calisanErkek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                karakterAyari(R.drawable.res_assets_profile_profilemalebusiness);
            }
        });
        iv_ogrenciErkek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                karakterAyari(R.drawable.res_assets_profile_profilemalestudent);
            }
        });
        iv_romantikErkek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                karakterAyari(R.drawable.res_assets_profile_profilemaleromantic);
            }
        });
        return view;
    }
    private void karakterAyari(int image){
        SharedPreferences pref = getActivity().getSharedPreferences("Images", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.putInt("IMG", image);
        ed.apply();
        Intent intent = new Intent(getActivity(), NavigationActivity.class);
        startActivity(intent);
    }
}