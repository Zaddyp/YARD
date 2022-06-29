package com.example.yard;
import androidx.appcompat.app.AppCompatActivity;
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

public class SignupActivity extends AppCompatActivity {
    private Button signUp;
    private TextView email;
    private TextView password;
    private TextView username;
    private TextView title;

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
                            // take the person to the log in page if the signup is successful and info has been stored in the back4app
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(SignupActivity.this, "success!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}