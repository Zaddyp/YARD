package com.example.yard;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yard.R;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignupActivity extends AppCompatActivity {
    private Button signUp;
    private TextView email;
    private TextView password;
    private TextView username;
    private TextView title;
    // HBCUs in Tennesse, Virginia, and Delaware
    String[] schools = {"my.fisk.edu", "lanecollege.edu", "my.tnstate.edu", "abcnash.edu", "KnoxvilleCollege.edu"
            , "loc.edu", "mmc.edu","hamptonu.edu", "nsu.edu", "desu.edu"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ParseInstallation.getCurrentInstallation().saveInBackground();
        signUp = findViewById(R.id.btnSigningup);
        email = findViewById(R.id.editTextEmailAddress);
        password = findViewById(R.id.tvPassword);
        username = findViewById(R.id.tvName);
        title = findViewById(R.id.tvTitle);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailAddress = email.getText().toString();
                String passwordString = password.getText().toString();
                String usernameString = username.getText().toString();
                String titLe = title.getText().toString();
//                convert the email to list before the @ and after and check if the end is in part of the schools listed
                String[] arrOfStr = emailAddress.split("@", 2);
                if (!Arrays.asList(schools).contains(arrOfStr[1])){
                    Toast.makeText(SignupActivity.this, "Your HBCU has to be in Tennesse, Virginia or Delaware", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (emailAddress.isEmpty() || passwordString.isEmpty() || usernameString.isEmpty()){
                    Toast.makeText(SignupActivity.this, "No field can be left empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                // upload information to the back4app
                ParseUser user = new ParseUser();
                user.setUsername(usernameString);
                user.setPassword(passwordString);
                user.setEmail(emailAddress);
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            ParseUser.logOut();
                            showAlert("Sign Up successful", "Please verify your School email address and login");
                        }
                        else{
                            showAlert("Account could not be created", e.getMessage());
                        }
                    }
                });
            }
        });
    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }).show();
    }
}