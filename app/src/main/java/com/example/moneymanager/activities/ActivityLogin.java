package com.example.moneymanager.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.R;
import com.example.moneymanager.database.DataBaseHelper;

public class ActivityLogin extends AppCompatActivity {

    TextView btnToRegister;
    EditText email, password;
    Button btnLogin;
    DataBaseHelper databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.etEmailLogin);
        password = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnToRegister = findViewById(R.id.btnToRegister);
        databaseUser = new DataBaseHelper(this);

        btnLogin.setOnClickListener((v) -> {

            String emails = email.getText().toString();
            String passwords = password.getText().toString();

            if( emails.equals("") || passwords.equals("")){
                Toast toast = Toast.makeText(ActivityLogin.this, "Please enter all the fields.", Toast.LENGTH_LONG);
                toast.show();
            }else{
                Boolean checkEmailPassword = databaseUser.checkEmailPassword(emails, passwords);
                if (checkEmailPassword == true){
                    Toast toast = Toast.makeText(ActivityLogin.this, "Login Successful!", Toast.LENGTH_LONG);
                    toast.show();

                    Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                    startActivity(intent);
                } else{
                    Toast toast = Toast.makeText(ActivityLogin.this, "Invalid Credentials.", Toast.LENGTH_LONG);
                    toast.show();
                }
            }

        });

        btnToRegister.setOnClickListener((v)-> {
            Intent intent = new Intent(getApplicationContext(), ActivityRegister.class);
            startActivity(intent);
        });

    }
}