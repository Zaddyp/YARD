package com.example.yard.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yard.R;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
  private TextView tvEmailAddress;
  private TextView password;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    if (ParseUser.getCurrentUser() != null) {
      goMainActivity();
    }
    tvEmailAddress = findViewById(R.id.tvEmailAddress);
    password = findViewById(R.id.etPassword);
    TextView signUp = findViewById(R.id.tvSign);
    Button logInButton = findViewById(R.id.btnLogin);
    signUp.setOnClickListener(
        view -> {
          Intent signUpIntent = new Intent(LoginActivity.this, SignupActivity.class);
          startActivity(signUpIntent);
        });
    logInButton.setOnClickListener(
        view -> {
          String stringEmail = tvEmailAddress.getText().toString();
          String stringPassword = password.getText().toString();
          loginUser(stringEmail, stringPassword);
        });
  }

  private void loginUser(String stringEmail, String stringPassword) {
    ParseUser.logInInBackground(
        stringEmail,
        stringPassword,
        (user, e) -> {
          if (e != null) {
            showAlert("Login failed", e.getMessage(), true);
            return;
          } else {
            showAlert("Login Successful", "Your email is verified successfully", false);
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
    builder
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(
            "OK",
            (dialogInterface, i) -> {
              dialogInterface.dismiss();
              if (!error) {
                goMainActivity();
              }
            })
        .show();
  }
}
