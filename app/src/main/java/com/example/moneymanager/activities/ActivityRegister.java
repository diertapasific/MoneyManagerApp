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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityRegister extends AppCompatActivity {

    EditText name, email, password;
    TextView btnToLogin;
    Button btnRegister;
    DataBaseHelper databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.etNameRegister);
        email = findViewById(R.id.etEmailRegister);
        password = findViewById(R.id.etPasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);
        btnToLogin = findViewById(R.id.btnToLogin);
        databaseUser = new DataBaseHelper(this);

            btnRegister.setOnClickListener((v) -> {

                String names = name.getText().toString();
                String emails = email.getText().toString();
                String passwords = password.getText().toString();

                if(names.equals("") || emails.equals("") || passwords.equals("")){
                    Toast toast = Toast.makeText(ActivityRegister.this, "Please enter all the fields.", Toast.LENGTH_LONG);
                    toast.show();
                } else{
                    Boolean checkUser = databaseUser.checkEmail(emails);
                    if( checkUser == false ){
                        Boolean insert = databaseUser.insertData(emails, names,passwords);
                        if( insert == true && isValid()){
                            Toast toast = Toast.makeText(ActivityRegister.this, "Registered Successfully!", Toast.LENGTH_LONG);
                            toast.show();

                            Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                            startActivity(intent);
                        } else{
                            Toast toast = Toast.makeText(ActivityRegister.this, "Registration Failed!", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                    else{
                        Toast toast = Toast.makeText(ActivityRegister.this, "Account already exists! Please Login.", Toast.LENGTH_LONG);
                        toast.show();
                    }
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

        return true;
    }
}

