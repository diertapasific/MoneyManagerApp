package com.example.moneymanager.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneymanager.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityRegister extends AppCompatActivity {

    EditText name, email, password;
    TextView btnToLogin;
    Button btnRegister;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        name = findViewById(R.id.etNameRegister);
        email = findViewById(R.id.etEmailRegister);
        password = findViewById(R.id.etPasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);
        btnToLogin = findViewById(R.id.btnToLogin);

        btnRegister.setOnClickListener((v) -> {

            String names = name.getText().toString();
            String emails = email.getText().toString();
            String passwords = password.getText().toString();

            if (isValid()) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username", names);
                editor.putString("email", emails);
                editor.putString("password", passwords);
                editor.apply();

                Toast toast2 = Toast.makeText(ActivityRegister.this, "Account Created!", Toast.LENGTH_LONG);
                toast2.show();
                Intent intent2 = new Intent(getApplicationContext(), ActivityLogin.class);
                startActivity(intent2);
            }
        });

        btnToLogin.setOnClickListener((v)-> {
            Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
            startActivity(intent);
        });

    }

    private boolean isValid() {

        String names = name.getText().toString();
        String emails = email.getText().toString();
        String passwords = password.getText().toString();

        Pattern patternPassword;
        Matcher matcherPassword;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{1,}$";
        patternPassword = Pattern.compile(PASSWORD_PATTERN);
        matcherPassword = patternPassword.matcher(passwords);

        // Check if userInput is valid
        if (names.isEmpty() || emails.isEmpty() || passwords.isEmpty()) {
            Toast toast = Toast.makeText(ActivityRegister.this, "All fields must be filled.", Toast.LENGTH_LONG);
            toast.show();
            return false;
        } else if (!names.isEmpty() && !emails.isEmpty() && !passwords.isEmpty()) {
            if (names.length() < 5) {
                Toast toast = Toast.makeText(ActivityRegister.this, "Full Name must be at least 5 characters long.", Toast.LENGTH_LONG);
                toast.show();
                return false;
            } else if (!emails.endsWith(".com")) {
                Toast toast2 = Toast.makeText(ActivityRegister.this, "Email must be ended by .com", Toast.LENGTH_LONG);
                toast2.show();
                return false;
            } else if (!matcherPassword.matches()) {
                Toast toast = Toast.makeText(ActivityRegister.this, "Password must be alphanumeric & has capital letter.", Toast.LENGTH_LONG);
                toast.show();
                return false;
            }
        }
        return true;
    }
}

