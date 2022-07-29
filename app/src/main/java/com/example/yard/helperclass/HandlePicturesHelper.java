package com.example.yard.helperclass;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;

public class HandlePicturesHelper {
  private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 45;
  private static final int GALLERY_REQUEST_CODE = 60;
  private static final String TAG = "HandlePictures";
  private static final String PHOTO_FILE_NAME = "photo.jpg";
  private File photoFile;

  public void launchCamera(Activity activity) {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    photoFile = getPhotoFileUri(PHOTO_FILE_NAME, activity);
    Uri fileProvider = FileProvider.getUriForFile(activity, "com.codepath.fileprovider", photoFile);
    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);
    if (intent.resolveActivity(activity.getPackageManager()) != null) {
      activity.startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }
  }

  public void fragmentLaunchCamera(Fragment fragment, File photoFile) {
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    photoFile = getPhotoFileUri(PHOTO_FILE_NAME, fragment.getActivity());
    Uri fileProvider =
        FileProvider.getUriForFile(fragment.getActivity(), "com.codepath.fileprovider", photoFile);
    intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);
    if (intent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
      fragment.startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }
  }

  public File getPhotoFileUri(String fileName, Activity activity) {
    File mediaStorageDir =
        new File(activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES), TAG);
    if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
      Log.d(TAG, "failed to create directory");
    }
    return new File(mediaStorageDir.getPath() + File.separator + fileName);
  }

  public String getPaths(Uri uri, Activity activity) {
    String[] projection = {MediaStore.Images.Media.DATA};
    Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);
    if (cursor == null) return null;
    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    cursor.moveToFirst();
    String strResult = cursor.getString(column_index);
    cursor.close();
    return strResult;
  }

  public void uploadImage(Activity activity) {
    Intent intent = new Intent(Intent.ACTION_PICK);
    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    if (intent.resolveActivity(activity.getPackageManager()) != null) {
      activity.startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }
  }

  public void fragmentUploadImage(Fragment fragment) {
    Intent intent = new Intent(Intent.ACTION_PICK);
    intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    if (intent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
      fragment.startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }
  }
}
