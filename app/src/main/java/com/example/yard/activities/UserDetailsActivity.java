package com.example.yard.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.yard.R;
import com.example.yard.adapter.Post;
import com.parse.ParseFile;

public class UserDetailsActivity extends AppCompatActivity {
  Post post;
  private ImageView ivUserImage;
  private TextView tvUserName;
  private TextView tvUserSchool;
  private TextView tvUserTitle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_details);
    ivUserImage = findViewById(R.id.ivUserImage);
    tvUserName = findViewById(R.id.tvUserName);
    tvUserSchool = findViewById(R.id.tvUserSchool);
    tvUserTitle = findViewById(R.id.tvUserTitle);
    post = (Post) getIntent().getExtras().get("Post");
    ParseFile userProfileImage = post.getKeyUser().getParseFile("profilepicture");
    String userName = post.getKeyUser().getUsername();
    String userSchool = (String) post.getKeyUser().get("school");
    String userTitle = (String) post.getKeyUser().get("title");
    tvUserName.setText(userName);
    tvUserTitle.setText(userTitle);
    tvUserSchool.setText(userSchool);
    Glide.with(this)
        .load(userProfileImage == null ? null : userProfileImage.getUrl())
        .into(ivUserImage);
  }
}
