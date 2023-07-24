package com.example.moneymanager.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.R;

public class ActivityLogin extends AppCompatActivity {

    TextView btnToRegister;
    EditText email, password;
    Button btnLogin;
    private SharedPreferences preferences, loginpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        email = findViewById(R.id.etEmailLogin);
        password = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnToRegister = findViewById(R.id.btnToRegister);

        btnLogin.setOnClickListener((v) -> {

            String emails = email.getText().toString().trim();
            String passwords = password.getText().toString().trim();

            // Validation Email
            if(emails.isEmpty() && passwords.isEmpty()){
                Toast toast = Toast.makeText(ActivityLogin.this, "All fields must be filled.", Toast.LENGTH_LONG);
                toast.show();
            }  else if (!emails.endsWith(".com")) {
                Toast toast2 = Toast.makeText(ActivityLogin.this, "Email must be ended by .com", Toast.LENGTH_LONG);
                toast2.show();
            } else if(emails.isEmpty()){
                Toast toast = Toast.makeText(ActivityLogin.this, "Please enter your email.", Toast.LENGTH_LONG);
                toast.show();
            } else if(passwords.isEmpty()){
                Toast toast = Toast.makeText(ActivityLogin.this, "Please enter your password.", Toast.LENGTH_LONG);
                toast.show();
            }

            String savedEmail = preferences.getString("email", "");
            String savedPassword = preferences.getString("password", "");

            Log.d("email", savedEmail);
            Log.d("pass", savedPassword );

            if (emails.equals(savedEmail) && passwords.equals(savedPassword)){
                Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                startActivity(intent);

                loginpref = getSharedPreferences("userlogin",MODE_PRIVATE);
                SharedPreferences.Editor loginedit = loginpref.edit();
                loginedit.putString("userlogin",emails);
                loginedit.apply();

                Toast toast = Toast.makeText(ActivityLogin.this, "Login Success!", Toast.LENGTH_LONG);
                toast.show();
            } else {
                // Login failed
                Toast toast = Toast.makeText(ActivityLogin.this, "Your email address or password incorrect.", Toast.LENGTH_LONG);
                toast.show();
            }

        });

        btnToRegister.setOnClickListener((v)-> {
            Intent intent = new Intent(getApplicationContext(), ActivityRegister.class);
            startActivity(intent);
        });

    }
}