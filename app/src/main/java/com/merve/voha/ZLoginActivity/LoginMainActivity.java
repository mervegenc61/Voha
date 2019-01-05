package com.merve.voha.ZLoginActivity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.merve.voha.R;
import com.merve.voha.ZWelcomeActivity.OneScreenWelcomeActivity;

public class LoginMainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    //değişken tanımlama
    Button btnkayit, btngiris2;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;
    private final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();//login için
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_login_activity_login_main);
        setup();
    }
    private void setup(){
        final SignInButton signInButton=findViewById(R.id.sign_in_button);
        final String text=getResources().getString(R.string.gmail);
        btnkayit = findViewById(R.id.kaydol);
        btngiris2 = findViewById(R.id.giris);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        setGooglePlusButtonText(signInButton,text);
        //Google Sign in Options Yapılandırması
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(LoginMainActivity.this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        //buton tıklanma eventları
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        btnkayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginMainActivity.this, RegisterActivity.class));
                finish();
            }
        });
        btngiris2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginMainActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
    //google buton üzerindeki yazı değişim fonksiyonu
    protected void setGooglePlusButtonText(SignInButton signInButton,
                                           String buttonText) {
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);
            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setTextSize(15);
                tv.setTypeface(null, Typeface.NORMAL);
                tv.setText(buttonText);
                tv.setTextColor(Color.WHITE);
                tv.setBackgroundResource(R.drawable.gmail_oval_btn);
                tv.setCompoundDrawablesWithIntrinsicBounds( R.drawable.common_google_signin_btn_icon_dark_normal, 0, 0, 0);
                tv.setPadding(18,0,0,0);
                return;
            }
        }
    }
    //gmail ile oturum açma işlemleri
    private void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In basarili oldugunda Firebase ile yetkilendir
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            }
        }
    }
    //GoogleSignInAccount nesnesinden ID token'ı alıp, bu Firebase güvenlik referansını kullanarak
    // Firebase ile yetkilendirme işlemini gerçekleştiriyorum
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        final AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginMainActivity.this, R.string.yetkilendirmeHatasi,
                                    Toast.LENGTH_SHORT).show();

                            Toast.makeText(LoginMainActivity.this, "", Toast.LENGTH_SHORT).show();
                        } else {
                            DatabaseReference databaseReferenceUsers = FirebaseDatabase.getInstance().getReference("users");
                            if(firebaseAuth.getCurrentUser()!=null) {
                                String email = firebaseAuth.getCurrentUser().getEmail();
                                String ad = firebaseAuth.getCurrentUser().getDisplayName();
                                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                                String id = mDatabase.child(firebaseAuth.getCurrentUser().getUid()).getKey();
                                Users users = new Users(id, ad, email);
                                databaseReferenceUsers.child(id).setValue(users);
                                startActivity(new Intent(LoginMainActivity.this, OneScreenWelcomeActivity.class));
                            }
                        }
                    }
                });}
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, R.string.googlePlayServisHatasi, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }
}







