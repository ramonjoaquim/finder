package com.quintus.labs.datingapp.Login;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.quintus.labs.datingapp.R;
import com.quintus.labs.datingapp.Utils.GPS;
import com.quintus.labs.datingapp.Utils.User;

import es.dmoral.toasty.Toasty;

/**
 * Created by Quintus Labs on 18-Dec-2018.
 * www.quintuslabs.com
 */
public class RegisterBasicInfo extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    GPS gps;
    private Context mContext;
    private String email, username, password, telefone;
    private EditText mEmail, mPassword, mUsername, mTelefone;
    private TextView loadingPleaseWait;
    private Button btnRegister;
    private String append = "";

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerbasic_info);
        mContext = RegisterBasicInfo.this;
        Log.d(TAG, "onCreate: started");

        gps = new GPS(getApplicationContext());

        initWidgets();
        init();
    }

    private void init() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();
                telefone = mTelefone.getText().toString();


                if (checkInputs(email, username, password, telefone)) {
                    //find geo location
                    //find geo location
                    Location location = gps.getLocation();
                    double latitude = 37.349642;
                    double longtitude = -121.938987;
                    if (location != null) {
                        latitude = location.getLatitude();
                        longtitude = location.getLongitude();
                    }
                    Log.d("Location==>", longtitude + "   " + latitude);


                    Intent intent = new Intent(RegisterBasicInfo.this, RegisterAge.class);
                    User user = new User(email,username,telefone,false,false,false,false,"");
                    intent.putExtra("password", password);
                    intent.putExtra("classUser", user);
                    intent.putExtra("telefone", telefone);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkInputs(String email, String username, String password, String telefone) {
        Log.d(TAG, "checkInputs: checking inputs for null values.");
        if (email.equals("") || username.equals("") || password.equals("") || telefone.equals("")) {
            Toasty.warning(mContext, "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.length() < 6){
            Toasty.warning(mContext, "Ops... sua senha deve ter no mínimo 6 dígitos!", Toast.LENGTH_LONG).show();
            return false;
        }

        if(telefone.length() < 10 ||
                telefone.equals("99999999999") ||
                telefone.equals("88888888888") ||
                telefone.equals("77777777777") ||
                telefone.equals("66666666666") ||
                telefone.equals("55555555555") ||
                telefone.equals("44444444444") ||
                telefone.equals("33333333333") ||
                telefone.equals("22222222222") ||
                telefone.equals("11111111111") ||
                telefone.equals("00000000000") ||

                telefone.equals("48999999999") ||
                telefone.equals("48888888888") ||
                telefone.equals("48777777777") ||
                telefone.equals("48666666666") ||
                telefone.equals("48555555555") ||
                telefone.equals("48444444444") ||
                telefone.equals("48333333333") ||
                telefone.equals("48222222222") ||
                telefone.equals("48111111111") ||
                telefone.equals("48000000000")
        )
        {
            Toast.makeText(mContext, "Ops... telefone inválido!", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Below code checks if the email id is valid or not.
        if (!email.contains("@")) {
            Toasty.warning(getApplicationContext(), "Digite um email válido!", Toast.LENGTH_SHORT).show();
            return false;

        }


        return true;
    }

    private void initWidgets() {
        Log.d(TAG, "initWidgets: initializing widgets");
        mEmail = findViewById(R.id.input_email);
        mUsername = findViewById(R.id.input_username);
        btnRegister = findViewById(R.id.btn_register);
        mPassword = findViewById(R.id.input_password);
        mTelefone = findViewById(R.id.input_telefone);
        mContext = RegisterBasicInfo.this;

    }

    public void onLoginClicked(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));

    }
}
