package com.quintus.labs.datingapp.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.quintus.labs.datingapp.Main.MainActivity;
import com.quintus.labs.datingapp.R;

import es.dmoral.toasty.Toasty;

/**
 * Created by Quintus Labs on 18-Dec-2018.
 * www.quintuslabs.com
 *
 * Modifcado By Ramon Joaquim Limas
 */
public class Login extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private Context mContext;
    private EditText mEmail, mPassword;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);
        mContext = Login.this;
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        init();
    }

    private boolean isStringNull(String string) {
        Log.d(TAG, "isStringNull: checking string if null.");

        return string.equals("");
    }

    //----------------------------------------Firebase----------------------------------------

    private void init() {
        //initialize the button for logging in
        final Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: attempting to log in");
                progressBar.setVisibility(View.VISIBLE);
                btnLogin.setClickable(false);
                btnLogin.setBackgroundColor(Color.parseColor("#B6B6B6"));
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if (isStringNull(email) || isStringNull(password)) {
                    Toasty.warning(mContext, "Ops.. Todos os campos devem ser preechidos!", Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.GONE);
                    btnLogin.setClickable(true);
                    btnLogin.setBackgroundColor(Color.parseColor("#69F0AE"));
                    return;
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    btnLogin.setClickable(false);
                    btnLogin.setBackgroundColor(Color.parseColor("#B6B6B6"));
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressBar.setVisibility(View.GONE);
                                    btnLogin.setClickable(true);
                                    btnLogin.setBackgroundColor(Color.parseColor("#69F0AE"));
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(Login.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            })

                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    progressBar.setVisibility(View.GONE);
                                    Toasty.error(mContext, "Ops.. email ou senha inválidos!", Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.GONE);
                                    btnLogin.setClickable(true);
                                    btnLogin.setBackgroundColor(Color.parseColor("#69F0AE"));

                                }
                            });


                }
            }
        });

        TextView linkSignUp = findViewById(R.id.link_signup);
        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: navigating to register screen");
                Intent intent = new Intent(Login.this, RegisterBasicInfo.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public void onBackPressed() {

    }


}
