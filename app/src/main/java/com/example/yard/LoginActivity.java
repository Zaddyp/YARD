package com.example.yard;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yard.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
public class LoginActivity extends AppCompatActivity {
    TextView signUp;
    TextView logEmail;
    TextView password;
    Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (ParseUser.getCurrentUser() != null){
            goMainActivity();
        }
        logEmail = findViewById(R.id.tvEmailAddress);
        password = findViewById(R.id.etPassword);
        signUp = findViewById(R.id.tvSign);
        logIn = findViewById(R.id.btnLogin);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(signUpIntent);
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringEmail = logEmail.getText().toString();
                String stringPassword = password.getText().toString();
                loginUser(stringEmail, stringPassword);
            }
        });
    }

    private void loginUser(String stringEmail, String stringPassword) {
        ParseUser.logInInBackground(stringEmail, stringPassword, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    showAlert("Login failed", e.getMessage(), true);
                    return;
                }
                else{
                    showAlert("Login Successful", "Your email is verified successfully", false);
                }
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void showAlert(String title, String message, Boolean error) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if(!error){
                    goMainActivity();
                }
            }
        }).show();
    }
}
