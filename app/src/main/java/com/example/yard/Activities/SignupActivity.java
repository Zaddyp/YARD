package com.example.yard.Activities;

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
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

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
  private TextView email;
  private TextView password;
  private TextView username;
  private TextView title;
  private TextView school;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);
    ParseInstallation.getCurrentInstallation().saveInBackground();
    Button signUp = findViewById(R.id.btnSigningup);
    email = findViewById(R.id.editTextEmailAddress);
    password = findViewById(R.id.tvPassword);
    school = findViewById(R.id.tvSchool);
    username = findViewById(R.id.tvName);
    title = findViewById(R.id.tvTitle);
    signUp.setOnClickListener(
            view -> {
              String emailAddress = email.getText().toString();
              String passwordString = password.getText().toString();
              String usernameString = username.getText().toString();
              String userTitle = title.getText().toString();
              String userSchool = school.getText().toString();
              //                convert the email to list before the @ and after and check if the end
              // is in part of the schools listed
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
              // upload information to the back4app
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
                                  "Sign Up successful",
                                  "Please verify your School email address and login");
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
