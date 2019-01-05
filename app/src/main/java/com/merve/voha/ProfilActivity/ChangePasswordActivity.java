package com.merve.voha.ProfilActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.merve.voha.R;

public class ChangePasswordActivity extends AppCompatActivity{
    Button btn_sifreGuncelle;
    EditText et_guncelSifre,et_yeniSifre,et_yeniSifreTekrar;
    ProgressDialog progressDialog;
    Boolean hata=false;
    String hata_mesaji="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        android.support.v7.widget.Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.sifreDegis);
        toolbar.setTitleTextColor(getResources().getColor(R.color.beyaz));
        setSupportActionBar(toolbar);
        btn_sifreGuncelle=findViewById(R.id.btn_sifreDegis);
        et_guncelSifre=findViewById(R.id.et_eskiSifre);
        et_yeniSifre=findViewById(R.id.et_yeniSifre);
        et_yeniSifreTekrar=findViewById(R.id.et_yeniSifreTekrar);
        progressDialog=new ProgressDialog(this);
    }
    public void sifreGuncelle(View view) {
        String guncelSifre, yeniSifre, yeniSifreTekrar;
        guncelSifre = et_guncelSifre.getText().toString().trim();
        yeniSifre = et_yeniSifre.getText().toString().trim();
        yeniSifreTekrar = et_yeniSifreTekrar.getText().toString().trim();
        if (guncelSifre.matches("") || yeniSifre.matches("") || yeniSifreTekrar.matches("")) {
            hata = true;
            hata_mesaji = getResources().getString(R.string.sifreBilgileriniGiriniz);
        } else if (yeniSifre.length() < 6) {
            hata_mesaji += R.string.sifre6karakterdenazolamaz;
            hata = true;
        } else if (!yeniSifreTekrar.matches(yeniSifre)) {
            hata = true;
            hata_mesaji += R.string.sifrelerEslesmiyor;
            et_yeniSifreTekrar.setText("");
        }
        if (hata) {
            AlertDialog alertDialog = new AlertDialog.Builder(ChangePasswordActivity.this).create();
            alertDialog.setTitle(R.string.kayitHatasi);
            alertDialog.setMessage(hata_mesaji);
            alertDialog.setCancelable(false);
          alertDialog.setButton(RESULT_OK, getResources().getString(R.string.tamam), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    hata_mesaji="";
                    hata = false;
                }
            });
            alertDialog.show();
        } else {//hata yoksa
            final FirebaseUser user;
            user = FirebaseAuth.getInstance().getCurrentUser();
            final String email = user.getEmail();
            AuthCredential credential = EmailAuthProvider.getCredential(email, et_guncelSifre.getText().toString());
            progressDialog.setMessage(getResources().getString(R.string.sifreDegistiriliyor));
            progressDialog.show();
            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        user.updatePassword(et_yeniSifre.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(ChangePasswordActivity.this, R.string.sifreDegistirilemedi, Toast.LENGTH_SHORT).show();
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(ChangePasswordActivity.this, R.string.sifreGuncellendi, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(ChangePasswordActivity.this, R.string.yanlisSifre, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ChangePasswordActivity.this,SettingsActivity.class));
        finish();
    }
}
