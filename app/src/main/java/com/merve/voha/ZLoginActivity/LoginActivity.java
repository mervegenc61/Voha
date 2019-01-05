package com.merve.voha.ZLoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.merve.voha.JavaEgitimleri.MainActivity;
import com.merve.voha.R;
import com.merve.voha.ZWelcomeActivity.OneScreenWelcomeActivity;


public class LoginActivity extends AppCompatActivity {
    private final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    Button login;
    EditText email,password;
    TextView tv_sifremiUnuttum;
    FloatingActionButton tv_hesabimYok;
    SignInButton signInButton;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_login_activity_login);
        setup();
    }
    private void setup(){
        email=findViewById(R.id.et_loginemail);
        password=findViewById(R.id.et_loginsifre);
        login=findViewById(R.id.btn_login);
        signInButton=findViewById(R.id.sign_in_button);
        tv_hesabimYok=findViewById(R.id.fab);
        tv_sifremiUnuttum=findViewById(R.id.tv_sifremiUnuttum);
        //login butonuna basınca...
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final String mail=email.getText().toString().trim();
                final String sifre=password.getText().toString().trim();
                //email veya şifre girilmemişse kullanıcıyı uyarıyoruz
                if(mail.matches("")|| sifre.matches("")){
                    Toast.makeText(LoginActivity.this, R.string.bilgGir, Toast.LENGTH_SHORT).show();
                }

                else {
                    firebaseAuth.signInWithEmailAndPassword(mail, sifre)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if ( task.isSuccessful() && checkIfEmailVerified()) {
                                        checkIfEmailVerified();
                                        databaseReference=FirebaseDatabase.getInstance().getReference("users");
                                            String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                            String deviceTokenId = FirebaseInstanceId.getInstance().getToken();
                                        databaseReference.child(currentUserId).child("device_token").setValue(deviceTokenId).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                startActivity(new Intent(LoginActivity.this,OneScreenWelcomeActivity.class));
                                                finish();
                                            }
                                        });
                                    }

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if( e instanceof FirebaseAuthInvalidUserException){
                                Toast.makeText(LoginActivity.this, R.string.kullaniciBulunamadi, Toast.LENGTH_SHORT).show();
                            }
                            if( e instanceof FirebaseAuthInvalidCredentialsException){
                                Toast.makeText(LoginActivity.this,  R.string.gecersizSifre, Toast.LENGTH_SHORT).show();
                            }
                            if(e instanceof FirebaseNetworkException){
                                Toast.makeText(LoginActivity.this,
                                        R.string.baglantıKontEt, Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });

    }
    //geri tuşuna basınca...
    @Override
    public void onBackPressed() {
        startActivity(new Intent(LoginActivity.this, LoginMainActivity.class));
        finish();
    }
    //email doğrulanmışmı...
    private boolean checkIfEmailVerified() {
        //şuanki kullanıcıyı referre ediyoruz ve nesneye atıyoruz...
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        boolean emailVerified = user.isEmailVerified();
        if(emailVerified==true){
            // Toast.makeText(LoginActivity.this, "Mail adresiniz doğrulandı...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            return true;}
        else{
            Toast.makeText(LoginActivity.this, "Lütfen mail adresinizi doğrulayınız...", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
    public void clickResetPassword(View view) {
        startActivity(new Intent(LoginActivity.this, com.merve.voha.ZLoginActivity.NewPasswordActivity.class));
        finish();
    }
    public void clickRegisterLayout(View view) {
        startActivity(new Intent(LoginActivity.this, com.merve.voha.ZLoginActivity.RegisterActivity.class));
        finish();
    }
}




