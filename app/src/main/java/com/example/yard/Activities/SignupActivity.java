package com.example.yard.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yard.R;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

import java.util.Arrays;

public class SignupActivity extends AppCompatActivity {
  // HBCUs in Tennesse, Virginia, and Delaware
  final String[] schools = {
    "my.fisk.edu",
    "lanecollege.edu",
    "my.tnstate.edu",
    "abcnash.edu",
    "KnoxvilleCollege.edu",
    "loc.edu",
    "mmc.edu",
    "hamptonu.edu",
    "nsu.edu",
    "desu.edu",
    "fb.com"
  };
  private TextView tvEmailAddress;
  private TextView tvPassword;
  private TextView tvUsername;
  private TextView tvTitle;
  private TextView tvSchool;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);
    ParseInstallation.getCurrentInstallation().saveInBackground();
    Button signUp = findViewById(R.id.btnSigningup);
    tvEmailAddress = findViewById(R.id.editTextEmailAddress);
    tvPassword = findViewById(R.id.tvPassword);
    tvSchool = findViewById(R.id.tvSchool);
    tvUsername = findViewById(R.id.tvName);
    tvTitle = findViewById(R.id.tvTitle);
    signUp.setOnClickListener(
        view -> {
          String emailAddress = tvEmailAddress.getText().toString();
          String passwordString = tvPassword.getText().toString();
          String usernameString = tvUsername.getText().toString();
          String userTitle = tvTitle.getText().toString();
          String userSchool = tvSchool.getText().toString();
          String[] arrOfStr = emailAddress.split("@", 2);

          if (emailAddress.isEmpty() || passwordString.isEmpty() || usernameString.isEmpty()) {
            Toast.makeText(SignupActivity.this, "No field can be left empty", Toast.LENGTH_SHORT)
                .show();
            return;
          }
          if (emailAddress.contains("@") && !Arrays.asList(schools).contains(arrOfStr[1])) {
            Toast.makeText(
                    SignupActivity.this,
                    "Your HBCU has to be in Tennesse, Virginia or Delaware",
                    Toast.LENGTH_SHORT)
                .show();
            return;
          }
          ParseUser user = new ParseUser();
          user.setUsername(usernameString);
          user.setPassword(passwordString);
          user.setEmail(emailAddress);
          user.put("school", userSchool);
          user.put("title", userTitle);
          user.signUpInBackground(
              e -> {
                if (e == null) {
                  ParseUser.logOut();
                  showAlert(
                      "Sign Up successful", "Please verify your School email address and login");
                } else {
                  showAlert("Account could not be created", e.getMessage());
                }
              });
        });
  }

  private void showAlert(String title, String message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(
            "OK",
            (dialogInterface, i) -> {
              dialogInterface.dismiss();
              Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
              startActivity(intent);
            })
        .show();
  }
}
