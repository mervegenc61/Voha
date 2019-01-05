package com.merve.voha.ZKarakterAyari;

import android.app.ProgressDialog;
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


public class FragmentKadin extends Fragment {
    ProgressDialog progressBar;
    public static FragmentKadin newInstance() {
        FragmentKadin fragment = new FragmentKadin();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.z_karakter_fragment_kadin, container, false);
        ImageView iv_istekliKadin=view.findViewById(R.id.istekliKadin);
        ImageView iv_gezginKadin=view.findViewById(R.id.gezginKadin);
        ImageView iv_calisanKadin=view.findViewById(R.id.calisanKadin);
        ImageView iv_ogrenciKadin=view.findViewById(R.id.ogrenciKadin);
        ImageView iv_romantikKadin=view.findViewById(R.id.romantikKadin);
        iv_istekliKadin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               karakterAyari(R.drawable.res_assets_profile_profilefemaleenthusiast);
            }
        });
        iv_gezginKadin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                karakterAyari( R.drawable.res_assets_profile_profilefemaletraveller);
            }
        });
        iv_calisanKadin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               karakterAyari(R.drawable.res_assets_profile_profilefemalebusiness);
            }
        });
        iv_ogrenciKadin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               karakterAyari(R.drawable.res_assets_profile_profilefemalestudent);
            }
        });
        iv_romantikKadin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               karakterAyari(R.drawable.res_assets_profile_profilefemaleromantic);
            }
        });
        return view;
    }
    private void karakterAyari(int image){
        SharedPreferences pref = getActivity().getSharedPreferences("Images", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = pref.edit();
        ed.putInt("IMG", image);
        ed.putInt("IMGG", image);
        ed.apply();
        Intent intent = new Intent(getActivity(), NavigationActivity.class);
        startActivity(intent);
    }
}