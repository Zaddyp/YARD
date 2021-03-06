package com.example.yard.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.yard.R;
import com.example.yard.adapter.Post;
import com.example.yard.fragments.BioFragment;
import com.example.yard.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

  private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
      item -> {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
          case R.id.nav_home:
            selectedFragment = new HomeFragment();
            break;
          case R.id.nav_profile:
            selectedFragment = new BioFragment();
            break;
        }
        assert selectedFragment != null;
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, selectedFragment)
            .commit();
        return true;
      };
  Post post;

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
    bottomNav.setOnNavigationItemSelectedListener(navListener);
    getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.fragment_container, new HomeFragment())
        .commit();
    bottomNav.setSelectedItemId(R.id.nav_home);
    FloatingActionButton preview = findViewById(R.id.btnPreview);
    preview.setOnClickListener(
        view -> {
          Intent intent = new Intent(MainActivity.this, PostActivity.class);
          startActivityForResult(intent, 100);
        });
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (post != null) {
      for (Fragment fragment : getSupportFragmentManager().getFragments()) {
        if (fragment instanceof HomeFragment) {
          ((HomeFragment) fragment).refreshPosts(post);
        }
      }
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      post = (Post) data.getExtras().get("Post");
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.log_out, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.itemLogout:
        ParseUser.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        return true;
      case R.id.btnDelete:
        deleteUser();
    }
    return super.onOptionsItemSelected(item);
  }

  private void deleteUser() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder
        .setTitle("DELETE ACCOUNT")
        .setMessage("CONFIRM: Do you want to delete your account ? ")
        .setNegativeButton(
            "NO",
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {}
            })
        .setPositiveButton(
            "YES",
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                ParseUser user = ParseUser.getCurrentUser();
                ParseQuery<Post> query = new ParseQuery<>("Post");
                query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
                query.findInBackground(
                    new FindCallback<Post>() {
                      @Override
                      public void done(List<Post> objects, ParseException e) {
                        for (Post object : objects) {
                          object.deleteInBackground(
                              new DeleteCallback() {
                                @Override
                                public void done(ParseException e) {}
                              });
                        }
                      }
                    });
                user.deleteInBackground(
                    new DeleteCallback() {
                      @Override
                      public void done(ParseException e) {
                        if (e == null) {
                          ParseUser.logOut();
                          Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                          startActivity(intent);
                        } else {
                          e.printStackTrace();
                        }
                      }
                    });
              }
            })
        .show();
  }
}
