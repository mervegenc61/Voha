package com.merve.voha.JavaEgitimleri;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.merve.voha.NavigationDrawer.NavigationActivity;
import com.merve.voha.R;
import com.merve.voha.Model.Score;
import com.merve.voha.Model.ToplamScore;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager mViewPager;
    CardPagerAdapter mCardAdapter;
    ShadowTransformer mCardShadowTransformer;
    CardFragmentPagerAdapter mFragmentCardAdapter;
    ShadowTransformer mFragmentCardShadowTransformer;
    Button btn;
    LottieAnimationView lottieAnimationView;
    TextView tv_info, tv_kodBilgi, tv_kodyazcalistir;
    EditText edt_kodyazcalistir;
    Button btn_soru, btn_A, btn_B, btn_C, btn_D, btn_goster, btn_kod, btn_kodcikti, btn_bitir, btn_kodyaz, btn_kodyazcalistir, btn_bitirr;
    CardView cv_kod,cv_score;
    AlertDialog.Builder builder;
    LinearLayout ll_btnsorucevap, ll_kodYazCalistir;
    CoordinatorLayout coordinatorLayout;
    //RelativeLayout rl_lottie;
    ImageView iv_ipucu;
    TextView tv_ipucu,tv_diamondScore,tv_goldScore;
    private FirebaseUser firebaseUserScore;
    private FirebaseAuth firebaseAuthScore;
    private FirebaseDatabase firebaseDatabaseScore;
    private FirebaseAuth.AuthStateListener firebaseAuthListenerUser,firebaseAuthListenerUserr;
    private FirebaseAuth firebaseAuthUser,firebaseAuthUserr;
    private DatabaseReference databaseReferenceScore;
    private String name;
    String get;
    public final static String Message = "com.merve.voha";
    int toplam=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_activity_main);
        setup();
        kullanıcıBilgileriniOku();
        if (get!=null && get.equalsIgnoreCase("c1")) {
            egitimler("c1");
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 4) {
                        soruYaz(R.color.turuncu, R.color.turuncu, R.color.mor_profil,
                               R.string.algsoru1,
                                R.string.alg1,
                                R.string.alg2,
                                R.string.alg3,
                                R.string.alg4, btn_B, btn_A, btn_C, btn_D,
                                0,
                                "c1",
                                0,
                                "",
                                0,
                                0
                        );
                    } else if (position == 7) {
                        kodCiktiGosterkodYazdir(
                                R.string.algbilgi,
                                R.string.algkod,
                                R.string.algcikti,
                                R.color.turuncu,
                                R.string.algsoru,
                                R.string.algkod2,
                                R.string.algcevap,
                                R.color.fistikyesili,
                                0,"c1",
                                R.string.tebriklerDogruCevap,"",R.string.algipucu,0,0
                        );

                    } else if (position == 8) {
                        kodCiktiGosterkodYazdir(
                                R.string.algsoru,
                                R.string.algkod3,
                                R.string.algcikti2,
                                 R.color.turuncu,
                                R.string.algsoru2,
                                R.string.algkod4,
                               R.string.algcevap2,

                                 R.color.fistikyesili,
                                1,"c1",
                                R.string.mesaj11,
                                "swing.json",
                               R.string.algipucu2,
                                10,
                                20);
                    }
                }
                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        } else if (get!=null && get.equalsIgnoreCase("c2")) {
            egitimler("c2");
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }
                @Override
                public void onPageSelected(int position) {
                    if (position == 8) {

                    } else if (position == 9) {

                    } else if (position == 12) {
                        soruYaz(R.color.fistikyesili, R.color.fistikyesili, R.color.mor_profil,
                                R.string.degiskenlersoru1,
                                R.string.deg1,
                                R.string.deg2,
                                R.string.deg3,
                                R.string.deg4, btn_C, btn_A, btn_B, btn_D,
                                0,
                                "c2",
                                R.string.tebriklerDogruCevap,
                                "",
                                0,
                                0);

                    } else if (position == 15) {
                       soruYaz(R.color.fistikyesili,R.color.fistikyesili,R.color.mor_profil,
                             R.string.degiskenlersoru2,
                              R.string.deg5,
                               R.string.deg6,
                               R.string.deg7,
                               R.string.deg8,btn_C,btn_A,btn_B,btn_D,
                               1,
                               "c6",
                              R.string.mesaj10,
                               "fireworks.json",
                               20,
                               40
                               );

                    }
                }
                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        } else if (get!=null && get.equalsIgnoreCase("c3")) {
            egitimler("c3");
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }
                @Override
                public void onPageSelected(int position) {
                    if (position == 11) {
                       animasyon("c3",R.string.mesaj9,"money.json",30,60);

                    }

                    else if (position == 6) {
                        kodYazAyri(
                                R.string.degsoru,
                                R.string.degkod,
                                R.string.degcevap,
                                R.color.fistikyesili, 0, "c3",0,"",R.string.degipucu,5,5
                        );
                    }
                }
                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        } else if (get!=null && get.equalsIgnoreCase("c4")) {
            egitimler("c4");
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }
                @Override
                public void onPageSelected(int position) {
                    if(position==6){
                        kodYazAyri(R.string.kontsoru,
                               R.string.kontkod,R.string.kontcevap,R.color.turuncu,0,"c4",R.string.tebriklerDogruCevap,"",R.string.kontipucu,0,0
                        );
                    }
                    else if(position==14){
                        animasyon("c4",R.string.mesaj8,"star.json",40,80);
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {


                }
            });
        } else if (get!=null && get.equalsIgnoreCase("c5")) {
            egitimler("c5");
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if(position==3){
                     soruYaz(R.color.welcomeyesil,R.color.welcomeyesil,R.color.mor_profil,R.string.funcsoru1,
                               R.string.func1,R.string.func2,R.string.func3,R.string.func4,btn_C,btn_A,btn_B,btn_D,0,"c5",R.string.tebriklerDogruCevap,
                                "",0,0);

                    }
                    else if(position==6){
                        kodCiktiGosterkodYazdir(R.string.fonkbilgi,
                                R.string.fonkkod,
                                R.string.fonkcevap,
                                 R.color.yesil,
                                R.string.fonksoru,
                                R.string.fonkkod2,
                                R.string.fonkcevap2,
                                R.color.fistikyesili,
                                1,
                                "c5",
                                R.string.mesaj5,
                                "patates.json",
                                R.string.fonkipucu,
                                 50,
                                 100
                        );

                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else if (get!=null && get.equalsIgnoreCase("c6")) {
            egitimler("c6");
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if(position==2){
                       soruYaz(R.color.turuncu,R.color.turuncu,R.color.mor_profil,R.string.dizisoru1,
                              R.string.dizi2,R.string.dizi3,R.string.dizi4,R.string.dizi5,
                                btn_A,btn_B,btn_C,btn_D,0,"c6",R.string.tebriklerDogruCevap,"",0,0);
                    }
                    else if(position==6){
                        kodCiktiGosterkodYazdir(R.string.dizibilgi,R.string.dizibilgi2,R.string.dizicevap,
                                R.color.sari,R.string.dizisoru,R.string.dizikod,R.string.dizicevap2,
                                R.color.fistikyesili,1,"c6",R.string.mesaj6,"cat.json",R.string.dizicevap2,60,120);
                    }

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else if (get!=null && get.equalsIgnoreCase("c7")) {
            egitimler("c7");
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                   if(position==3){
                     soruYaz(R.color.turkuaz,R.color.turkuaz,R.color.mor_profil,R.string.yapisoru1,
                             R.string.yapi2,R.string.yapi3,R.string.yapi4,R.string.yapi5,
                               btn_C,btn_A,btn_B,btn_D,1,"c7",R.string.mesaj7,
                               "smiley_stack.json",70,140);
                   }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else if (get!=null && get.equalsIgnoreCase("c8")) {
            egitimler("c8");
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                      if(position==4){
                           soruYaz(R.color.sarii,R.color.sarii,R.color.mor_profil,R.string.kalitimsoru1,
                                   R.string.super2,R.string.extends2,R.string.implements2,R.string.string,
                                   btn_B,btn_A,btn_C,btn_D,0,"c8",R.string.tebriklerDogruCevap,"",0,0);
                       }
                       else if(position==12){
                           soruYaz(R.color.sarii,R.color.sarii,R.color.mor_profil,R.string.kalitimsoru2,
                                   R.string.metodoverriding,R.string.extends2,R.string.implements2,R.string.toString,
                                   btn_A,btn_B,btn_C,btn_D,1,"c8",R.string.mesaj4,"noodles.json",80,160);
                       }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else if (get!=null && get.equalsIgnoreCase("c9")) {
            egitimler("c9");
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                  if(position==1){
                     soruYaz(R.color.ckirmizi,R.color.ckirmizi,R.color.mor_profil,R.string.polisoru1,
                              R.string.Kalitim,R.string.Dongu,R.string.Polimorfizm,R.string.Yapilandirici,
                              btn_C,btn_B,btn_A,btn_D,1,"c9",R.string.mesaj3,"happy_gift.json",90,180);
                  }

                }
                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else if (get!=null && get.equalsIgnoreCase("c10")) {
            egitimler("c10");
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                   if(position==7){
                      soruYaz(R.color.colorPrimary,R.color.colorPrimary,R.color.mor_profil,R.string.soyutsoru1,
                               R.string.kalitim,R.string.interface2,R.string.polimorfizm,R.string.abstract2,
                               btn_D,btn_B,btn_A,btn_C,1,"c10",R.string.mesaj2,"biking_is_cool.json",100,200);
                   }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else if (get!=null && get.equalsIgnoreCase("c11")) {
            egitimler("c11");
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }
                @Override
                public void onPageSelected(int position) {
                   if(position==6){
                       soruYaz(R.color.colorPrimaryDark,R.color.colorPrimaryDark,R.color.mor_profil,R.string.arayüzsoru1,
                               R.string.kalitim, R.string.interface2, R.string.polimorfizm, R.string.abstract2,
                               btn_B,btn_D,btn_A,btn_C,1,"c11", R.string.mesaj1,"graduating_engineer.json",120,240);
                   }
                }
                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
       cardAdapterYapisi();
    }

    private void dogruCevap(final int color) {
        mViewPager.setVisibility(View.VISIBLE);
        btn_soru.setVisibility(View.INVISIBLE);
        btn_A.setVisibility(View.INVISIBLE);
        btn_B.setVisibility(View.INVISIBLE);
        btn_C.setVisibility(View.INVISIBLE);
        btn_D.setVisibility(View.INVISIBLE);
        ll_btnsorucevap.setBackgroundColor(getResources().getColor(color));

    }
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
    public void alertDialogYanlisCevap() {
        builder.setTitle(R.string.voha);
        builder.setMessage(R.string.yanlisCevap2);
        builder.setIcon(R.drawable.cryingface);
        builder.setPositiveButton(R.string.tamam, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }

        }); // Buttonu ve tıklanma olayını ekledik. İster tıklanma olayına bir şeyler yazarsınız, ister de boş bırakırsınız. Size kalmış. Biz boş bıraktık. Tıklantığında diyalog kapanacak.
        AlertDialog alert = builder.create(); // Daha sonra builder'ı AlertDialog'a aktarıyoruz.
        alert.show();// En sonunda ise AlertDialog'umuzu gösteriyoruz.
    }
    private void setup() {
        btn = findViewById(R.id.btn_);
        btn_soru = findViewById(R.id.tv_algoritmasoru);
        btn_kod = findViewById(R.id.btn_kod);
        btn_kodcikti = findViewById(R.id.btn_kodcikti);
        btn_A = findViewById(R.id.btn_optionA);
        btn_B = findViewById(R.id.btn_optionB);
        btn_C = findViewById(R.id.btn_optionC);
        btn_D = findViewById(R.id.btn_optionD);
        btn_goster = findViewById(R.id.btn_goster);
        btn_kodyaz = findViewById(R.id.btn_kodyaz);
        btn_kodyazcalistir = findViewById(R.id.btn_kodyazCalistir);
        btn_bitirr = findViewById(R.id.btn_bitir);




        coordinatorLayout = findViewById(R.id.coordinatorlayout);
        cv_kod = findViewById(R.id.cv_kod);
        edt_kodyazcalistir = findViewById(R.id.edt_kodyazCalistir);

        iv_ipucu=findViewById(R.id.iv_ipucu);


        ll_btnsorucevap = findViewById(R.id.ll_btnsorucevap);
        ll_kodYazCalistir = findViewById(R.id.ll_kodyazcalistir);

        tv_kodBilgi = findViewById(R.id.tv_kodBilgi);
        tv_kodyazcalistir = findViewById(R.id.tv_kodyazCalistir);
        tv_ipucu=findViewById(R.id.tv_ipucu);

        builder = new AlertDialog.Builder(this);

        Intent intent = getIntent();
        get = intent.getStringExtra(MainActivity.Message);
        mViewPager = findViewById(R.id.viewPager);
    }
    public void kodCiktiGosterkodYazdir(final int bilgi, final int kod, final int cikti,
                                        final int color, final int soru, final int kod2,
                                        final int cevap, final int color2, final int animasyon,
                                        final String bolumId,
                                        final int mesaj,
                                        final String animasyonCesidi,
                                        final int ipucu, final int goldScore, final int diamondScore) {
        mViewPager.setVisibility(View.INVISIBLE);
        cv_kod.setVisibility(View.VISIBLE);
        tv_kodBilgi.setText(bilgi);
        btn_kod.setText(kod);
        btn_goster.setText(R.string.calistir);
        btn_goster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_kodcikti.setText(cikti);
                btn_goster.setText(R.string.ileri);
                GradientDrawable gradientDrawable = (GradientDrawable) btn_goster.getBackground();
                gradientDrawable.setColor(getResources().getColor(color));
                btn_goster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cv_kod.setVisibility(View.INVISIBLE);
                        btn_kodcikti.setText("");
                        kodYazAyri(soru, kod2, cevap, color2,animasyon,bolumId,mesaj,animasyonCesidi,ipucu,goldScore,diamondScore);
                    }
                });
            }

        });
    }

    public void kodYazAyri(final int soru, final int kod, final int cevap,
                           final int color, final int animasyon, final String bolumId,
                           final int mesaj, final String animasyonCesidi, final int ipucu,
                           final int goldScore, final int diamondScore) {
        mViewPager.setVisibility(View.INVISIBLE);
        ll_kodYazCalistir.setVisibility(View.VISIBLE);
        tv_kodyazcalistir.setText(soru);
        btn_kodyaz.setText(kod);
        btn_kodyazcalistir.setText(R.string.calistir);
        btn_kodyazcalistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_kodyazcalistir.getText().toString().equals(getResources().getString(cevap))) {
                    Toast.makeText(MainActivity.this, R.string.tebriklerDogruCevap, Toast.LENGTH_SHORT).show();
                    btn_kodyazcalistir.setText(R.string.ileri);
                    GradientDrawable gradientDrawable = (GradientDrawable) btn_kodyazcalistir.getBackground();
                    gradientDrawable.setColor(getResources().getColor(color));
                    btn_kodyazcalistir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mViewPager.setVisibility(View.VISIBLE);
                            ll_kodYazCalistir.setVisibility(View.INVISIBLE);
                            edt_kodyazcalistir.setText("");
                            if(animasyon==1){
                                animasyon(bolumId,mesaj,animasyonCesidi,goldScore,diamondScore);
                            }
                            iv_ipucu.setVisibility(View.INVISIBLE);
                            tv_ipucu.setText("");



                        }
                    });

                } else {
                    Toast.makeText(MainActivity.this, R.string.yanlisCevap, Toast.LENGTH_SHORT).show();
                    ipucuGoster(ipucu);

                }
            }
        });
    }
    public void soruYaz(final int color1, final int color2, final int color3,
                        final int soru, final int secenekA, final int secenekB,
                        final int secenekC, final int secenekD, final Button dogruCevap,
                        final Button yanlisCevap1, final Button yanlisCevap2, final Button yanlisCevap3,
                        final int IsAnimasyon, final String bolumId, final int mesaj, final String animasyonCesidi,
                        final int goldScore, final int diamondScore) {
        mViewPager.setVisibility(View.INVISIBLE);
        ll_btnsorucevap.setVisibility(View.VISIBLE);
        ll_btnsorucevap.setBackgroundColor(getResources().getColor(color1));
        btn_soru.setVisibility(View.VISIBLE);
        btn_A.setVisibility(View.VISIBLE);
        btn_B.setVisibility(View.VISIBLE);
        btn_C.setVisibility(View.VISIBLE);
        btn_D.setVisibility(View.VISIBLE);
        btn_soru.setTextColor(getResources().getColor(color2));
        btn_soru.setText(soru);
        btn_A.setText(secenekA);
        btn_B.setText(secenekB);
        btn_C.setText(secenekC);
        btn_D.setText(secenekD);
        dogruCevap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, R.string.tebriklerDogruCevap, Toast.LENGTH_SHORT).show();
                dogruCevap(color3);
                if(IsAnimasyon==1) {
                    animasyon(bolumId,mesaj,animasyonCesidi,goldScore,diamondScore);
                }

            }
        });
        yanlisCevap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogYanlisCevap();
            }
        });
        yanlisCevap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogYanlisCevap();
            }
        });
        yanlisCevap3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogYanlisCevap();
            }
        });


    }
    public void kodCiktiGoster(final String bilgi, final String kod, final String cikti, final int color) {
        mViewPager.setVisibility(View.INVISIBLE);
        cv_kod.setVisibility(View.VISIBLE);
        tv_kodBilgi.setText(bilgi);
        btn_kod.setText(kod);
        btn_goster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_kodcikti.setText(cikti);
                btn_goster.setText(R.string.ileri);
                GradientDrawable gradientDrawable = (GradientDrawable) btn_goster.getBackground();
                gradientDrawable.setColor(getResources().getColor(color));
                btn_goster.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mViewPager.setVisibility(View.VISIBLE);
                        cv_kod.setVisibility(View.INVISIBLE);
                        btn_kodcikti.setText("");
                    }
                });
            }
        });
    }

    public void animasyon(final String bolumId, final int mesaj, final String animasyonCesidi,
                          final int goldScore, final int diamondScore) {
        btn_bitirr.setVisibility(View.VISIBLE);
        btn_bitirr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                }, 10);
                new Handler().postDelayed(new Runnable() {
                                              @Override
                                              public void run() {
                                                  setContentView(R.layout.lottie);
                                                  cv_score=findViewById(R.id.cv_score);
                                                  tv_goldScore=findViewById(R.id.btn_goldScore);
                                                  tv_diamondScore=findViewById(R.id.btn_diamondScore);
                                                  cv_score.setVisibility(View.VISIBLE);
                                                  tv_goldScore.setText(String.valueOf(goldScore));
                                                  tv_diamondScore.setText(String.valueOf(diamondScore));


                                                  // rl_lottie=findViewById(R.id.rl_lottie);

                                                  //  rl_lottie.setBackgroundColor(getResources().getColor(R.color.turuncu));


                                                  lottieAnimationView = findViewById(R.id.lottie_stars);
                                                  lottieAnimationView.setVisibility(View.VISIBLE);
                                                  lottieAnimationView.setAnimation(animasyonCesidi);
                                                  lottieAnimationView.loop(true);
                                                  lottieAnimationView.playAnimation();
                                                  tv_info = findViewById(R.id.info);
                                                  tv_info.setVisibility(View.VISIBLE);
                                                  tv_info.setText(mesaj);
                                                  btn_bitir = findViewById(R.id.btn_lotbitir);
                                                  btn_bitir.setVisibility(View.VISIBLE);
                                                  btn_bitir.setOnClickListener(new View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View view) {
                                                          //database bağlantısı açıp score bilgisini kayıt edeceğiz.
                                                          //örneklerini alıyoruz
                                                          firebaseAuthScore=FirebaseAuth.getInstance();
                                                          firebaseDatabaseScore=FirebaseDatabase.getInstance();
                                                          firebaseUserScore=firebaseAuthScore.getCurrentUser();
                                                          //score adında bir düğüm açıyorum
                                                          databaseReferenceScore=firebaseDatabaseScore.getReference("score");
                                                          final int toplamScore=goldScore+diamondScore;
                                                          String id=databaseReferenceScore.push().getKey();
                                                          final Score score=new Score(id,firebaseUserScore.getUid(),name,bolumId,goldScore,diamondScore,toplamScore);
                                                          databaseReferenceScore.child(id).setValue(score);
                                                          Query query=databaseReferenceScore.orderByChild("userId").equalTo(firebaseUserScore.getUid());

                                                          query.addValueEventListener(new ValueEventListener() {
                                                              @Override
                                                              public void onDataChange(DataSnapshot dataSnapshot) {
                                                                  ArrayList<Integer> list = new ArrayList<>();
                                                                  for (DataSnapshot child: dataSnapshot.getChildren()) {
                                                                      Score score1 = child.getValue(Score.class);
                                                                      list.add((score1.getTotalbolum()));
                                                                      for(int i=0;i<list.size();i++){
                                                                          toplam=toplam+list.get(i);
                                                                          DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("toplam");
                                                                         // String id=databaseReferenceScore.push().getKey();
                                                                          ToplamScore toplamScore1=new ToplamScore(FirebaseAuth.getInstance().getCurrentUser().getUid(),name,toplam);
                                                                          databaseReference.child(firebaseAuthScore.getCurrentUser().getUid()).setValue(toplamScore1);
                                                                      }
                                                                  }
                                                              }

                                                              @Override
                                                              public void onCancelled(DatabaseError databaseError) {

                                                              }
                                                          });

                                                          startActivity(new Intent(MainActivity.this, NavigationActivity.class));
                                                          finish();

                                                      }
                                                  });
                                              }
                                          },
                        10);
            }
        });

    }
    public void egitimler(Object button){
        if(button=="c1") {
            mCardAdapter = new CardPagerAdapter();
            mCardAdapter.addCardItem(new CardItem(R.string.global_title, R.string.algoritma1, R.drawable.cicek));
            mCardAdapter.addCardItem(new CardItem(R.string.global_title, R.string.algoritma2, R.drawable.cicek));
            mCardAdapter.addCardItem(new CardItem(R.string.global_title, R.string.algoritma3, R.drawable.cicek));
            mCardAdapter.addCardItem(new CardItem(R.string.global_title, R.string.algoritma4, R.drawable.cicek));
            mCardAdapter.addCardItem(new CardItem(R.string.global_title, R.string.algoritma5, R.drawable.cicek));
            mCardAdapter.addCardItem(new CardItem(R.string.global_title, R.string.algoritma6, R.drawable.cicek));
            mCardAdapter.addCardItem(new CardItem(R.string.global_title, R.string.javagenel, R.drawable.cicek));
            mCardAdapter.addCardItem(new CardItem(R.string.global_title, R.string.javagenel1, R.drawable.cicek));
            mCardAdapter.addCardItem(new CardItem(R.string.global_title, R.string.javagenel2, R.drawable.cicek));

        }
        else if(button=="c2"){
            //coordinatorLayout.setBackgroundColor(getResources().getColor(R.color.fistikyesili));
            mCardAdapter = new CardPagerAdapter();
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik4, R.string.degisken1, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik4, R.string.degisken2, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik4, R.string.degisken3, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik4, R.string.degisken4, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik5, R.string.degisken5, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik6, R.string.degisken6, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik7, R.string.degisken7, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik8, R.string.degisken8, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik9, R.string.degisken9, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik10, R.string.degisken10, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik11, R.string.degisken11, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik12, R.string.degisken12, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik13, R.string.degisken13, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik14, R.string.degisken14, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik13, R.string.degisken15, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.degbaslik16, R.string.degisken16, R.drawable.agac));
        }
        else if(button=="c3"){
            mCardAdapter = new CardPagerAdapter();
            mCardAdapter.addCardItem(new CardItem(R.string.opbaslik2, R.string.operatörler1, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.opbaslik2, R.string.operatörler2, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.opbaslik3, R.string.operatörler3, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.opbaslik3, R.string.operatörler4, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.opbaslik5, R.string.operatörler5, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.opbaslik5, R.string.operatörler6, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.opbaslik7, R.string.operatörler7, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.opbaslik7, R.string.operatörler8, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.opbaslik7, R.string.operatörler9, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.opbaslik7, R.string.operatörler10, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.opbaslik7, R.string.operatörler11, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.opbaslik7, R.string.operatörler12, R.drawable.agac));

        }
        else if(button=="c4"){
            mCardAdapter = new CardPagerAdapter();
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik12, R.string.kontrolyapilari1, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik12, R.string.kontrolyapilari2, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik3, R.string.kontrolyapilari3, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik45, R.string.kontrolyapilari4, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik45, R.string.kontrolyapilari5, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik67, R.string.kontrolyapilari6, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik67, R.string.kontrolyapilari7, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik89, R.string.kontrolyapilari8, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik89, R.string.kontrolyapilari9, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik10, R.string.kontrolyapilari10, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik11, R.string.kontrolyapilari11, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik1213, R.string.kontrolyapilari12, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik1213, R.string.kontrolyapilari13, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik1415, R.string.kontrolyapilari14, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kontbaslik1415, R.string.kontrolyapilari15, R.drawable.agac));

        }
        else if(button=="c5"){
            mCardAdapter = new CardPagerAdapter();
            mCardAdapter.addCardItem(new CardItem(R.string.fonkbaslik12, R.string.fonksiyonlar1, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.fonkbaslik12, R.string.fonksiyonlar2, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.fonkbaslik12, R.string.fonksiyonlar3, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.fonkbaslik456, R.string.fonksiyonlar4, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.fonkbaslik456, R.string.fonksiyonlar5, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.fonkbaslik456, R.string.fonksiyonlar6, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.fonkbaslik7, R.string.fonksiyonlar7, R.drawable.agac));

        }
        else if(button=="c6"){
            mCardAdapter = new CardPagerAdapter();
            mCardAdapter.addCardItem(new CardItem(R.string.dizibaslik1234, R.string.diziler1, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.dizibaslik1234, R.string.diziler2, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.dizibaslik1234, R.string.diziler3, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.dizibaslik1234, R.string.diziler4, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.dizibaslik567, R.string.diziler5, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.dizibaslik567, R.string.diziler6, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.dizibaslik567, R.string.diziler7, R.drawable.agac));
        }
        else if(button=="c7"){
            mCardAdapter = new CardPagerAdapter();
            mCardAdapter.addCardItem(new CardItem(R.string.yapbaslik123, R.string.yapilandiricilar1, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.yapbaslik123, R.string.yapilandiricilar2, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.yapbaslik123, R.string.yapilandiricilar3, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.yapbaslik123, R.string.yapilandiricilar4, R.drawable.agac));

        }
        else if(button=="c8"){
            mCardAdapter = new CardPagerAdapter();
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik123458, R.string.kalitim1, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik123458, R.string.kalitim2, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik123458, R.string.kalitim3, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik123458, R.string.kalitim4, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik123458, R.string.kalitim5, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik6, R.string.kalitim6, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik7, R.string.kalitim7, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik123458, R.string.kalitim8, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik9, R.string.kalitim9, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik1011, R.string.kalitim10, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik1011, R.string.kalitim11, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik12, R.string.kalitim12, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.kbaslik13, R.string.kalitim13, R.drawable.agac));

        }
        else if(button=="c9"){
            mCardAdapter = new CardPagerAdapter();
            mCardAdapter.addCardItem(new CardItem(R.string.polibaslik123, R.string.polimorfizm1, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.polibaslik123, R.string.polimorfizm2, R.drawable.agac));


        }
        else if(button=="c10"){
            mCardAdapter = new CardPagerAdapter();
            mCardAdapter.addCardItem(new CardItem(R.string.sbaslik1234, R.string.soyutsiniflar1, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.sbaslik1234, R.string.soyutsiniflar2, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.sbaslik1234, R.string.soyutsiniflar3, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.sbaslik1234, R.string.soyutsiniflar4, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.sbaslik5, R.string.soyutsiniflar5, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.sbaslik678, R.string.soyutsiniflar6, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.sbaslik678, R.string.soyutsiniflar7, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.sbaslik678, R.string.soyutsiniflar8, R.drawable.agac));

        }
        else if(button=="c11"){
            mCardAdapter = new CardPagerAdapter();
            mCardAdapter.addCardItem(new CardItem(R.string.arabaslik12345, R.string.arayuzler1, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.arabaslik12345, R.string.arayuzler2, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.arabaslik12345, R.string.arayuzler3, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.arabaslik12345, R.string.arayuzler4, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.arabaslik12345, R.string.arayuzler5, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.arabaslik6, R.string.arayuzler6, R.drawable.agac));
            mCardAdapter.addCardItem(new CardItem(R.string.arabaslik7, R.string.arayuzler7, R.drawable.agac));

        }
    }
    public void cardAdapterYapisi(){

        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),
                dpToPixels(2, this));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mCardShadowTransformer.enableScaling(true);
        mFragmentCardShadowTransformer.enableScaling(true);
    }
    public void ipucuGoster(final int ipucu){
        iv_ipucu.setVisibility(View.VISIBLE);
        iv_ipucu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_ipucu.setVisibility(View.VISIBLE);
                tv_ipucu.setText(ipucu);
            }
        });

    }
    private void kullanıcıBilgileriniOku(){
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
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
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
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.cikmakIstediginizeEminMisiniz)
                .setCancelable(false)
                .setPositiveButton(R.string.evet, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.hayir, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();


    }

  }


