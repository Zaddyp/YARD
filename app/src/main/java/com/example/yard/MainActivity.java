package com.example.yard;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button takePicture;
    private Button submit;
    private Button upload;
    private TextView etdescription;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        takePicture = findViewById(R.id.btnTakePicture);
        submit = findViewById(R.id.btnSubmit);
        upload = findViewById(R.id.btnUploadPicture);
        etdescription = findViewById(R.id.tvDescription);
        image = findViewById(R.id.ivImage);

        takePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = etdescription.getText().toString();

                if (description.isEmpty()){
                    Toast.makeText(MainActivity.this, "Description cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                savePost(description, currentUser);
            }
        });
    }

    private void savePost(String description, ParseUser currentUser) {
        PostCreation postCreation = new PostCreation();
        postCreation.setKeyDescription(description);
//        postCreation.setImage();
        postCreation.setKeyUser(currentUser);
        postCreation.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Log.e("error", "error while saving");
                    Toast.makeText(MainActivity.this, "error while saving", Toast.LENGTH_SHORT).show();
                    etdescription.setText("");
                }
            }
        });
    }

    private void queryPosts() {
        ParseQuery<PostCreation> query = ParseQuery.getQuery(PostCreation.class);
        query.include(PostCreation.KEY_USER);
        query.findInBackground(new FindCallback<PostCreation>() {
            @Override
            public void done(List<PostCreation> posts, ParseException e) {
                if (e != null){
                    Toast.makeText(MainActivity.this, "The list of posts is empty and the query isnt getting anything", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }

}