package com.example.yard.fragments;

import static android.app.Activity.RESULT_OK;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.yard.R;
import com.example.yard.adapter.SectionPagerAdapter;
import com.example.yard.helperclass.HandlePicturesHelper;
import com.google.android.material.tabs.TabLayout;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.File;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

@SuppressWarnings("ALL")
public class BioFragment extends Fragment {
  private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 45;
  private static final int GALLERY_REQUEST_CODE = 60;
  private static final String TAG = "HandlePictures";
  private static final String PHOTO_FILE_NAME = "photo.jpg";
  public CircleImageView ivProfilePicture;
  HandlePicturesHelper takePicture = new HandlePicturesHelper();
  private View myFragment;
  private ViewPager viewPager;
  private TabLayout tabLayout;
  private TextView tvProfileName;
  private TextView tvUserSchool;
  private TextView tvUserTitle;
  private File photoFile;

  public BioFragment() {}

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    myFragment = inflater.inflate(R.layout.fragment_bio, container, false);
    viewPager = myFragment.findViewById(R.id.view_pager);
    tabLayout = myFragment.findViewById(R.id.tab_layout);
    ivProfilePicture = myFragment.findViewById(R.id.ivProfilePicture);
    return myFragment;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    tvProfileName = Objects.requireNonNull(getActivity()).findViewById(R.id.tvProfileName);
    tvUserSchool = getActivity().findViewById(R.id.tvProfileschool);
    tvUserTitle = getActivity().findViewById(R.id.tvUserTitle);
    ParseUser currentUser = ParseUser.getCurrentUser();
    tvProfileName.setText(currentUser.getUsername());
    tvUserSchool.setText(currentUser.getString("school"));
    tvUserTitle.setText(currentUser.getString("title"));
    setUpViewPager(viewPager);
    ParseFile profilePicture = currentUser.getParseFile("profilepicture");
    if (profilePicture != null) {
      Glide.with(getContext()).load(profilePicture.getUrl()).into(ivProfilePicture);
    }
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.addOnTabSelectedListener(
        new TabLayout.OnTabSelectedListener() {
          @Override
          public void onTabSelected(TabLayout.Tab tab) {}

          @Override
          public void onTabUnselected(TabLayout.Tab tab) {}

          @Override
          public void onTabReselected(TabLayout.Tab tab) {}
        });
    ivProfilePicture.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            choosePicture();
          }
        });
  }

  private void choosePicture() {
    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
    builder
        .setTitle("CHOOSE PICTURE")
        .setMessage("CONFIRM: Choose a photo")
        .setNegativeButton(
            "UPLOAD",
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                HandlePicturesHelper uploadImage = new HandlePicturesHelper();
                uploadImage.fragmentUploadImage(BioFragment.this);
              }
            })
        .setPositiveButton(
            "TAKE PICTURE",
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                HandlePicturesHelper handlePicturesHelper = new HandlePicturesHelper();
                photoFile = handlePicturesHelper.getPhotoFileUri(PHOTO_FILE_NAME, getActivity());
                takePicture.fragmentLaunchCamera(BioFragment.this, photoFile);
              }
            })
        .show();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    HandlePicturesHelper takenPicture = new HandlePicturesHelper();
    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
      if (resultCode == RESULT_OK) {
        Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
        ivProfilePicture.setImageBitmap(takenImage);
        ParseUser parseUser = ParseUser.getCurrentUser();
        ParseFile file = new ParseFile(photoFile);
        file.saveInBackground();
        parseUser.put("profilepicture", file);
        parseUser.saveInBackground();
      } else { // Result was a failure
        Toast.makeText(getContext(), "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
      }
    }
    if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK) {
      Uri fileUri = data.getData();
      ivProfilePicture.setImageURI(fileUri);
      photoFile = new File(takenPicture.getPaths(fileUri, getActivity()));
      ParseFile file = new ParseFile(photoFile);
      ParseUser parseUser = ParseUser.getCurrentUser();
      file.saveInBackground();
      parseUser.put("profilepicture", file);
      parseUser.saveInBackground();
    }
  }

  private void setUpViewPager(ViewPager viewPager) {
    SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());
    adapter.addFragment(new BioGridFragment(), "POSTS");
    adapter.addFragment(new BioTagFragment(), "TAGS");
    viewPager.setAdapter(adapter);
  }
}
