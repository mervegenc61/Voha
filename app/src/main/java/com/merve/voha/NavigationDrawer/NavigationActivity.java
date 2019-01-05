package com.merve.voha.NavigationDrawer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.merve.voha.ClassAdmob;
import com.merve.voha.JavaEgitimleri.MainActivity;
import com.merve.voha.ProfilActivity.SettingsActivity;
import com.merve.voha.R;

import at.markushi.ui.CircleButton;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView nav_header_name, nav_header_emal;
    ImageView nav_header_imag;
    public final static String Message = "com.merve.voha";
    CircleButton c1, c2, c3, c4, c5, c6, c7, c8, c9, c10,c11;
    private ProgressDialog progressBar;
    private FirebaseAuth.AuthStateListener firebaseAuthListenerUser;
    private FirebaseAuth firebaseAuthUser;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_activity_navigation);
        readUserInfo();
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
       // nav_header_imag = header.findViewById(R.id.nav_header_image);
        nav_header_emal=header.findViewById(R.id.nav_header_email);
        nav_header_name=header.findViewById(R.id.nav_header_name);
        SharedPreferences pref = getSharedPreferences("Images", Context.MODE_PRIVATE);
        /*int res = pref.getInt("IMG",0);
        if(res!=0){
            nav_header_imag.setImageResource(res);
        }*/
        @NonNull
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        nav_header_emal.setText(firebaseAuth.getCurrentUser().getEmail());

        c1 =  findViewById(R.id.b1);
        c2 =  findViewById(R.id.b2);
        c3 =  findViewById(R.id.b3);
        c4 =  findViewById(R.id.b4);
        c5 =  findViewById(R.id.b5);
        c6 =  findViewById(R.id.b6);
        c7 =  findViewById(R.id.b7);
        c8 =  findViewById(R.id.b8);
        c9 =  findViewById(R.id.b9);
        c10 =  findViewById(R.id.b10);
        c11=findViewById(R.id.b11);


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage(getResources().getString(R.string.info1));//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                        intent.putExtra(Message, "c1");//by this statement we are sending the name of the button that invoked the new Questions.java activity "Message" is defined by the name of the package + MESSAGE
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage(getResources().getString(R.string.info2));//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                        intent.putExtra(Message, "c2");
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage(getResources().getString(R.string.info3));//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                        intent.putExtra(Message, "c3");
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage(getResources().getString(R.string.info4));//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                        intent.putExtra(Message, "c4");
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage(getResources().getString(R.string.info5));//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                        intent.putExtra(Message, "c5");
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage(getResources().getString(R.string.info6));//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                        intent.putExtra(Message, "c6");
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage(getResources().getString(R.string.info7));//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                        intent.putExtra(Message, "c7");
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage(getResources().getString(R.string.info8));//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                        intent.putExtra(Message, "c8");
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage(getResources().getString(R.string.info9));//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                        intent.putExtra(Message, "c9");
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage(getResources().getString(R.string.info10));//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                        intent.putExtra(Message, "c10");
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To show button click
                new Handler().postDelayed(new Runnable() {@Override public void run(){}}, 400);
                progressBar = new ProgressDialog(v.getContext());//Create new object of progress bar type
                progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any wher on screen
                progressBar.setMessage(getResources().getString(R.string.info11));//Title shown in the progress bar
                progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                progressBar.setProgress(0);//attributes
                progressBar.setMax(100);//attributes
                progressBar.show();//show the progress bar
                //This handler will add a delay of 3 seconds
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Intent start to open the navigation drawer activity
                        progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                        Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                        intent.putExtra(Message, "c11");
                        startActivity(intent);
                    }
                }, 2000);
            }
        });
        final InterstitialAd mInterstitialAd = ClassAdmob.getInstance(getApplicationContext());
        AdRequest adRequest = ClassAdmob.getAdRequest();
        if (mInterstitialAd != null) {
            mInterstitialAd.loadAd(adRequest);
        }
        assert mInterstitialAd != null;
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                if (mInterstitialAd.isLoaded()) {
                    //reklam yüklenmişse gösteriliyor
                    mInterstitialAd.show();
                }
                Log.i("Ads", "onAdLoaded");
            }
            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
            }
            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.i("Ads", "onAdOpened");
            }
            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }
            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Log.i("Ads", "onAdClosed");
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        /*@Override
public boolean onKeyDown(int keyCode, KeyEvent event)
{
    if ((keyCode == KeyEvent.KEYCODE_BACK))
    {
        finish();
    }
    return super.onKeyDown(keyCode, event);
}*/

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        @NonNull
        int id = item.getItemId();
        if (id == R.id.nav_ogren) {
        }
        else if(id == R.id.oduller){
            startActivity(new Intent(NavigationActivity.this,OdullerActivity.class));

        }
        else if(id==R.id.liderler){
            startActivity(new Intent(this,BasariSiralamasi.class));
        }
        else if (id == R.id.nav_profil) {
                    startActivity(new Intent(NavigationActivity.this, SettingsActivity.class));

        } else if (id == R.id.nav_share) {
            //shareApplication();
            Intent intent = new Intent(android.content.Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Voha");
            System.out.println(""+R.string.email_content);
            intent.putExtra(Intent.EXTRA_TEXT, ""+getText(R.string.email_content)+getText(R.string.link)+getText(R.string.last_content));
            Intent chooser = Intent.createChooser(intent, getResources().getString(R.string.kullanicilarlaPaylas));
            startActivity(chooser);
        } else if (id == R.id.nav_feedback) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("mailto:"));
            String[] recipents = {"vohalingo@gmail.com"};
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, recipents);
            intent.putExtra(Intent.EXTRA_SUBJECT, R.string.vohaHakkinda);
            Intent chooser = Intent.createChooser(intent, getResources().getString(R.string.geriBildGonder));
            startActivity(chooser);
        } else if (id == R.id.nav_Help) {
            Intent i = new Intent(this, HelpActivity.class);
            startActivity(i);
        }
        else if(id==R.id.nav_aboutus){
            Intent i = new Intent(this, AboutActivity.class);
            startActivity(i);
        }
        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
       @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    private void readUserInfo(){
        firebaseAuthUser= FirebaseAuth.getInstance();
        firebaseAuthListenerUser =new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            name=(String.valueOf(dataSnapshot.child("ad").getValue()));
                            nav_header_name.setText(name);
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(NavigationActivity.this, "databaseError"+databaseError, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        };
    }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuthUser.addAuthStateListener(firebaseAuthListenerUser);
    }


}

