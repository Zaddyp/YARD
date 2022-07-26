package com.example.yard.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.yard.R;
import com.example.yard.fragments.BioFragment;
import com.example.yard.fragments.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.parse.DeleteCallback;
import com.parse.ParseUser;

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
    ImageView image = findViewById(R.id.ivImage);
    FloatingActionButton preview = findViewById(R.id.btnPreview);
    preview.setOnClickListener(
        view -> {
          Intent intent = new Intent(MainActivity.this, PostActivity.class);
          startActivity(intent);
        });
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
                    user.deleteInBackground(
                        new DeleteCallback() {
                          @Override
                          public void done(com.parse.ParseException e) {
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
    return super.onOptionsItemSelected(item);
  }
}
