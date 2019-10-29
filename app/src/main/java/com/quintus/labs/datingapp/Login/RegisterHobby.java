package com.quintus.labs.datingapp.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.quintus.labs.datingapp.Main.MainActivity;
import com.quintus.labs.datingapp.R;
import com.quintus.labs.datingapp.Utils.User;

import es.dmoral.toasty.Toasty;

/**
 * Created by Quintus Labs on 18-Dec-2018.
 * www.quintuslabs.com
 */

public class RegisterHobby extends AppCompatActivity {
    private static final String TAG = "RegisterHobby";

    //User Info
    User userInfo;
    String password;
    ProgressBar progressBar;
    private Context mContext;
    private Button hobbiesContinueButton;
    private Button sportsSelectionButton;
    private Button travelSelectionButton;
    private Button musicSelectionButton;
    private Button fishingSelectionButton;
    private Button bancoSelectionButton;
    private Button internetDasCoisasSelectionButton;
    private Button empreendedorismoSelectionButton;
    private Button redesSelectionButton;
    public int programacao ,informatica ,analise ,suporte = 0;
    public static  boolean isCreateUser = false;
    FirebaseAuth firebaseAuth;

    private FirebaseAuth mAuth;

    private String append = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_hobby);
        mContext = RegisterHobby.this;
        firebaseAuth = FirebaseAuth.getInstance();

        Log.d(TAG, "onCreate: started");

        Intent intent = getIntent();
        userInfo = (User) intent.getSerializableExtra("classUser");
        password = intent.getStringExtra("password");

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);



        initWidgets();

        init();
    }

    private void initWidgets() {
        sportsSelectionButton = findViewById(R.id.sportsSelectionButton);
        travelSelectionButton = findViewById(R.id.travelSelectionButton);
        musicSelectionButton = findViewById(R.id.musicSelectionButton);
        fishingSelectionButton = findViewById(R.id.fishingSelectionButton);
        hobbiesContinueButton = findViewById(R.id.hobbiesContinueButton);
        bancoSelectionButton = findViewById(R.id.bancoButton);
        internetDasCoisasSelectionButton = findViewById(R.id.internetDasCoisasButton);
        redesSelectionButton = findViewById(R.id.redesButton);
        empreendedorismoSelectionButton = findViewById(R.id.empreendedorismoButton);

        // Initially all the buttons needs to be grayed out so this code is added, on selection we will enable it later
        sportsSelectionButton.setAlpha(.5f);
        sportsSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));

        travelSelectionButton.setAlpha(.5f);
        travelSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));

        musicSelectionButton.setAlpha(.5f);
        musicSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));

        fishingSelectionButton.setAlpha(.5f);
        fishingSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));

        bancoSelectionButton.setAlpha(.5f);
        bancoSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));


        //continuar
        internetDasCoisasSelectionButton.setAlpha(.5f);
        internetDasCoisasSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));

        redesSelectionButton.setAlpha(.5f);
        redesSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));

        empreendedorismoSelectionButton.setAlpha(.5f);
        empreendedorismoSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));

        bancoSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bancoSelectionButtonClicked();
            }
        });


        sportsSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sportsButtonClicked();
            }
        });

        travelSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travelButtonClicked();
            }
        });

        musicSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musicButtonClicked();
            }
        });

        fishingSelectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fishingButtonClicked();
            }
        });


    }

    public void bancoSelectionButtonClicked(){
        // this is to toggle between selection and non selection of button
        if (bancoSelectionButton.getAlpha() == 1.0f) {
            bancoSelectionButton.setAlpha(.5f);
            bancoSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));
            //userInfo.setProgramacao(false);
        } else {
            bancoSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));
            bancoSelectionButton.setAlpha(1.0f);
            //userInfo.setProgramacao(true);
        }
    }

    public void sportsButtonClicked() {
        // this is to toggle between selection and non selection of button
        if (sportsSelectionButton.getAlpha() == 1.0f) {
            sportsSelectionButton.setAlpha(.5f);
            sportsSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));
            userInfo.setProgramacao(false);
            programacao = 0;
        } else {
            sportsSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));
            sportsSelectionButton.setAlpha(1.0f);
            userInfo.setProgramacao(true);
            programacao = 1;
        }
    }

    public void travelButtonClicked() {
        // this is to toggle between selection and non selection of button
        if (travelSelectionButton.getAlpha() == 1.0f) {
            travelSelectionButton.setAlpha(.5f);
            travelSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));
            userInfo.setInformatica(false);
            informatica = 0;
        } else {
            travelSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));
            travelSelectionButton.setAlpha(1.0f);
            userInfo.setInformatica(true);
            informatica = 1;
        }

    }

    public void musicButtonClicked() {
        // this is to toggle between selection and non selection of button
        if (musicSelectionButton.getAlpha() == 1.0f) {
            musicSelectionButton.setAlpha(.5f);
            travelSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));
            userInfo.setAnalise(false);
            analise = 0;
        } else {
            musicSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));
            musicSelectionButton.setAlpha(1.0f);
            userInfo.setAnalise(true);
            analise = 1;
        }

    }

    public void fishingButtonClicked() {
        // this is to toggle between selection and non selection of button
        if (fishingSelectionButton.getAlpha() == 1.0f) {
            fishingSelectionButton.setAlpha(.5f);
            travelSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));
            userInfo.setSuporte(false);
            suporte = 0;
        } else {
            fishingSelectionButton.setBackgroundColor(Color.parseColor("#69F0AE"));
            fishingSelectionButton.setAlpha(1.0f);
            userInfo.setSuporte(true);
            suporte = 1;
        }

    }


    public boolean verificaSelecao(){
        boolean tf =false;
        if(analise == 0 && suporte == 0 && programacao == 0 && informatica == 0){
            Toasty.warning(mContext, "Selecione pelo menos um interesse!", Toast.LENGTH_SHORT).show();
            tf =  false;
        }else{
            tf = true;
        }
        return tf;
    }

    public void init() {
        final Button hobbiesContinueButton = findViewById(R.id.hobbiesContinueButton);
        hobbiesContinueButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    hobbiesContinueButton.setBackgroundColor(Color.parseColor("#B6B6B6"));
                    if (verificaSelecao() == false) {
                        progressBar.setVisibility(View.GONE);
                        hobbiesContinueButton.setClickable(true);
                        hobbiesContinueButton.setBackgroundColor(Color.parseColor("#69F0AE"));
                        Toasty.warning(mContext, "Tente novamente!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        if (createUser()) {
                            FirebaseFirestore.getInstance().collection("usuarios")
                                    .add(userInfo)

                                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentReference> task) {
                                            progressBar.setVisibility(View.GONE);
                                            hobbiesContinueButton.setClickable(true);
                                            Toasty.success(mContext, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();

                                            Intent intent = new Intent(RegisterHobby.this, MainActivity.class);
                                            startActivity(intent);

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            progressBar.setVisibility(View.GONE);
                                            hobbiesContinueButton.setClickable(true);
                                            hobbiesContinueButton.setBackgroundColor(Color.parseColor("#69F0AE"));
                                            Toasty.error(mContext, "Ops.. sem resposta no servidor :(", Toast.LENGTH_SHORT).show();

                                        }
                                    });


                        } else {
                            progressBar.setVisibility(View.GONE);
                            hobbiesContinueButton.setClickable(true);
                            hobbiesContinueButton.setBackgroundColor(Color.parseColor("#69F0AE"));
                            Toasty.info(mContext, "Tente novamente!", Toast.LENGTH_SHORT).show();
                            return;

                        }
                    }
                }

          });
    }


    //----------------------------------------Firebase----------------------------------------


    public boolean createUser () {
        String email = userInfo.getEmail();
        String senha = password;

        firebaseAuth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            isCreateUser = true;
                        }
                        else{
                           // Toasty.error(getApplicationContext(),"Ocorreu um erro :(",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        return isCreateUser;
    }

    @Override
    protected void onStart() {
        super.onStart();

    }


}
