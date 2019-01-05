package com.merve.voha.ProfilActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.merve.voha.R;
import com.merve.voha.ZLoginActivity.LoginMainActivity;
import com.merve.voha.ZLoginActivity.Users;

public class SettingsActivity extends AppCompatActivity {
    TextView tv_settingsAdSoyad, tv_setEmail, tv_settingSifreDegis, tv_settingsSes, tv_settingsKarakterDegis, tv_settingsDavetEt, tv_settingsEmail,tv_settingCikis;
    Button btn_settingsGuncelle;
    EditText et_settingsAdSoyad;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference_settings;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.profil);
        toolbar.setTitleTextColor(getResources().getColor(R.color.beyaz));
        setSupportActionBar(toolbar);
        setup();
        verileriOku();
    }

    private void setup() {
        tv_settingsAdSoyad = findViewById(R.id.tv_settingsAdSoyad);
        tv_setEmail = findViewById(R.id.tv_setEmail);
        tv_settingSifreDegis = findViewById(R.id.tv_settingSifreDegis);
      //  tv_settingsKarakterDegis = findViewById(R.id.tv_settingsKarakterDegis);
        tv_settingsDavetEt = findViewById(R.id.tv_settingsDavetEt);
        tv_settingsEmail = findViewById(R.id.tv_settingsEmail);
        tv_settingCikis=findViewById(R.id.tv_settingsCikis);
        et_settingsAdSoyad = findViewById(R.id.et_settingsAdSoyad);
        btn_settingsGuncelle = findViewById(R.id.btn_settingsGuncelle);
        Editable etext = et_settingsAdSoyad.getText();
        Selection.setSelection(etext, et_settingsAdSoyad.getText().toString().length());
        et_settingsAdSoyad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_settingsAdSoyad.setSelection(et_settingsAdSoyad.getText().length());
                et_settingsAdSoyad.setCursorVisible(true
                );
            }
        });

        tv_settingSifreDegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sifreDegis();
            }
        });
    /*   tv_settingsKarakterDegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                karakterDegis();
            }
        });*/
        btn_settingsGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_settingsGuncelle.setBackgroundColor(getResources().getColor(R.color.fistikyesili));

                if(et_settingsAdSoyad.length()<3){
                    Toast.makeText(SettingsActivity.this, R.string.profilHata1, Toast.LENGTH_SHORT).show();
                }
                else if(et_settingsAdSoyad.length()>15){
                    Toast.makeText(SettingsActivity.this, R.string.profilHata2, Toast.LENGTH_SHORT).show();
                }
                else{
                    verileriGuncelle();
                }

            }
        });
        tv_settingsDavetEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                davetEt();
            }
        });
        tv_settingCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth=FirebaseAuth.getInstance();
                mAuth.signOut();
                startActivity(new Intent(SettingsActivity.this, LoginMainActivity.class));
                finish();
            }
        });
    }
    private void verileriOku() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    databaseReference_settings = FirebaseDatabase.getInstance().getReference().child("users");
                    databaseReference_settings.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            et_settingsAdSoyad.setText(String.valueOf(dataSnapshot.child("ad").getValue()));
                            tv_settingsEmail.setText(String.valueOf(dataSnapshot.child("email").getValue()));
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            }
        };
    }

    private void sifreDegis() {
        startActivity(new Intent(SettingsActivity.this, ChangePasswordActivity.class));
    }

    /*private void karakterDegis() {
        startActivity(new Intent(SettingsActivity.this, KarakterActivity.class));
    }*/

    private void verileriGuncelle() {
        ProgressDialog(R.string.bilgilerinizGuncellniyor,R.string.bilgilerinizGuncellendi);
        databaseReference_settings = FirebaseDatabase.getInstance().getReference("users").child(mAuth.getCurrentUser().getUid());
        Users users = new Users(databaseReference_settings.push().getKey(), et_settingsAdSoyad.getText().toString(), tv_settingsEmail.getText().toString());
        databaseReference_settings.setValue(users);
    }

    private void davetEt() {
        mailWithShare();
        whatsappWithShare();

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void mailWithShare() {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Vohalingo");
        System.out.println("" + R.string.email_content);
        intent.putExtra(Intent.EXTRA_TEXT, "" + getText(R.string.email_content) + getText(R.string.link) + getText(R.string.last_content));
        Intent chooser = Intent.createChooser(intent, getResources().getString(R.string.paylas));
        startActivity(chooser);
    }

    private void whatsappWithShare() {
        PackageManager pm = getPackageManager();
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            intent.setPackage("com.whatsapp");
            intent.putExtra(Intent.EXTRA_TEXT, "" + getText(R.string.email_content) + getText(R.string.link) + getText(R.string.last_content));
            Intent chooser = Intent.createChooser(intent, getResources().getString(R.string.paylas));
            startActivity(chooser);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.wp, Toast.LENGTH_SHORT)
                    .show();
        }
    }
    private void ProgressDialog(int mesaj, final int mesaj2){
        final ProgressDialog progressBar;
        new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
        progressBar = new ProgressDialog(SettingsActivity.this);//Create new object of progress bar type
        progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
        progressBar.setMessage(getResources().getString(mesaj));//Title shown in the progress bar
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
        progressBar.setProgress(0);//attributes
        progressBar.setMax(100);//attributes
        progressBar.setIcon(R.mipmap.a);
        progressBar.show();//show the progress bar
        //This handler will add a delay of 3 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent start to open the navigation drawer activity
                progressBar.setIcon(R.mipmap.a);
                progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                Toast.makeText(SettingsActivity.this, mesaj2, Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}
