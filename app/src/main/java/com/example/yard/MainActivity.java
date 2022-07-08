package com.example.yard;
<<<<<<< HEAD

=======
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
>>>>>>> eb5cd50 (fragments, posts, email verification for only HBCUs in Tennessee, Delaware, and Virginia)
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private FloatingActionButton preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        bottomNav.setSelectedItemId(R.id.nav_home);
        image = findViewById(R.id.ivImage);
        preview = findViewById(R.id.btnPreview);
        preview.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Post.class);
                startActivity(intent);
            }
        });
    }
<<<<<<< HEAD

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

=======
    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_events:
                    selectedFragment = new EventFragment();
                    break;
                case R.id.nav_profile:
                    selectedFragment = new BioFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.log_out, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemLogout:
                ParseUser.logOut();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
//    private void queryPosts() {
//        ParseQuery<PostCreation> query = ParseQuery.getQuery(PostCreation.class);
//        query.include(PostCreation.KEY_USER);
//        query.findInBackground(new FindCallback<PostCreation>() {
//            @Override
//            public void done(List<PostCreation> posts, ParseException e) {
//                if (e != null){
//                    Toast.makeText(MainActivity.this, "The list of posts is empty and the query isnt getting anything", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//        });
//    }
>>>>>>> eb5cd50 (fragments, posts, email verification for only HBCUs in Tennessee, Delaware, and Virginia)
}