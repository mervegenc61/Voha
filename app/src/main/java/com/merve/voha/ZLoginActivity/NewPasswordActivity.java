package com.merve.voha.ZLoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.merve.voha.R;


public class NewPasswordActivity extends AppCompatActivity {

    EditText email;
    Button yeniParolaGonder;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_login_activity_new_password);

        email = findViewById(R.id.uyeEmail);
        yeniParolaGonder = findViewById(R.id.yeniParolaGonder);


        //FirebaseAuth sınıfının referans olduğu nesneleri kullanabilmek için getInstance methodunu kullanıyoruz.
        auth = FirebaseAuth.getInstance();


        yeniParolaGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = email.getText().toString().trim();

                if (TextUtils.isEmpty(mail)) {
                    Toast.makeText(NewPasswordActivity.this, R.string.mailAdresiniGir, Toast.LENGTH_SHORT).show();
                    return;
                }


                //FirebaseAuth sınıfı üzerinden sendPasswordResetEmail nesnesi ile girilen maile parola sıfırlama bağlantısı gönderebiliyoruz.
                auth.sendPasswordResetEmail(mail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(NewPasswordActivity.this, R.string.yeniParolaBagGonderildi, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(NewPasswordActivity.this, R.string.mailGondermeHatasi, Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(NewPasswordActivity.this, LoginMainActivity.class));
        finish();
    }


}
