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
  private TextView tvPassword;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    if (ParseUser.getCurrentUser() != null) {
      goMainActivity();
    }
    tvEmailAddress = findViewById(R.id.tvEmailAddress);
    tvPassword = findViewById(R.id.etPassword);
    TextView tvSignUp = findViewById(R.id.tvSign);
    Button btnLogIn = findViewById(R.id.btnLogin);
    tvSignUp.setOnClickListener(
        view -> {
          Intent signUpIntent = new Intent(LoginActivity.this, SignupActivity.class);
          startActivity(signUpIntent);
        });
    btnLogIn.setOnClickListener(
        view -> {
          String strEmail = tvEmailAddress.getText().toString();
          String strPassword = tvPassword.getText().toString();
          loginUser(strEmail, strPassword);
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
