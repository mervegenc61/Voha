package com.merve.voha.ZLoginActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.merve.voha.R;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    Button btn_kayitOl;
    EditText et_adsoyad,mail, et_sifre,et_sifretekrar;
    private DatabaseReference databaseReferenceUsers;
    private final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    private ProgressDialog mProgress;
    boolean hata = false;
    String hata_mesaji="";
    List<Users> userses;
    TextView tv_girisyap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_login_activity_register);
        setup();
    }
    private void setup(){
        databaseReferenceUsers = FirebaseDatabase.getInstance().getReference("users");
        et_adsoyad = findViewById(R.id.et_registerad);
        mail= findViewById(R.id.et_registermail);
        et_sifre = findViewById(R.id.et_registersifre);
        et_sifretekrar = findViewById(R.id.et_registersifretekrar) ;
        btn_kayitOl = findViewById(R.id.btn_kayitOl);
        tv_girisyap=findViewById(R.id.tv_girisyap);
        mProgress = new ProgressDialog(this);
        userses =new ArrayList<>();
        btn_kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();

            }
        });
        tv_girisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
    private void startRegister() {
        final String ad = et_adsoyad.getText().toString().trim();
        final String email = mail.getText().toString().trim();
        final String sifre = et_sifre.getText().toString().trim();
        final String sifretekrar=et_sifretekrar.getText().toString().trim();
        //gerekli kontroller...
        if (ad.matches("")  || email.matches("") || sifre.matches("") || sifretekrar.matches("")) {
            hata = true;
            hata_mesaji = getResources().getString(R.string.gerekliAlanlariDoldurunuz);
        }
        else if (sifre.length() < 6) {
            hata_mesaji += R.string.sifre6karakterdenazolamaz;
            hata = true;
        }

        else if(!sifretekrar.matches(sifre)){
            hata=true;
            hata_mesaji+=R.string.sifrelerEslesmiyor;
            et_sifre.setText("");
            et_sifretekrar.setText("");
        }

        //Mail format kontrol...
        else if(!Fonksiyonlar.isEmailValid(email)) {
            hata_mesaji += R.string.yanlisMailFormati;
            hata = true;
        }

        //hata varsa hatayı alertDialog ile gosteriyorum...
        if (hata) {
            AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
            alertDialog.setTitle(R.string.kayitHatasi2);
            alertDialog.setMessage(hata_mesaji);
            alertDialog.setCancelable(false);
            alertDialog.setButton(RESULT_OK, getResources().getString(R.string.tamam), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    et_sifre.setText("");
                    hata_mesaji = "";
                    hata = false;
                }
            });
            alertDialog.show();
        } else {//hata yoksa
            mProgress.setMessage(getResources().getString(R.string.kayitOluyosun));
            mProgress.show();
            firebaseAuth.createUserWithEmailAndPassword(email, sifre)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mProgress.dismiss();
                            //İşlem başarısız olursa kullanıcıya bir Toast mesajıyla bildiriyoruz.
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, R.string.zatenKayitlisin,
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {
                                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                                    String id = mDatabase.child(firebaseAuth.getCurrentUser().getUid()).getKey();
                                    Users users = new Users(id, ad, email);
                                    databaseReferenceUsers.child(id).setValue(users);
                                    sendVerificationEmail();
                            }
                        }

                    });
        }
    }
    private void sendVerificationEmail() {
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, R.string.mailGonderildi, Toast.LENGTH_SHORT).show();
                            FirebaseAuth.getInstance().signOut();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            finish();
                        }
                        else {
                            user.sendEmailVerification();
                            overridePendingTransition(0, 0);
                            finish();
                            overridePendingTransition(0, 0);
                            startActivity(getIntent());
                        }
                    }
                });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

}