package com.example.yard.Adapter;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Post")
public class PostCreation extends ParseObject {

  public static final String KEY_DESCRIPTION = "description";
  public static final String KEY_IMAGE = "image";
  public static final String KEY_USER = "user";
  public static final String KEY_CREATED_AT = "createdAt";
  public static final String KEY_LOCATION = "location";
  public static final String KEY_SCHOOL = "school";

  public String getKeyDescription() {
    return getString(KEY_DESCRIPTION);
  }

  public void setKeyDescription(String description) {
    put(KEY_DESCRIPTION, description);
  }

  public String getKeyLocation() {
    return getString(KEY_LOCATION);
  }

  public void setKeyLocation(String location) {
    put(KEY_LOCATION, location);
  }

  // --Commented out by Inspection START (7/9/22, 4:02 AM):
  //    // --Commented out by Inspection (7/9/22, 4:02 AM):public String getKeySchool() {return
  // getString(KEY_SCHOOL);}
  //    public void setKeySchool(String school) {put(KEY_SCHOOL, school);}
  // --Commented out by Inspection STOP (7/9/22, 4:02 AM)

  public ParseFile getImage() {
    return getParseFile(KEY_IMAGE);
  }

  public void setImage(ParseFile parseFile) {
    put(KEY_IMAGE, parseFile);
  }

  public ParseUser getKeyUser() {
    return getParseUser(KEY_USER);
  }

  public void setKeyUser(ParseUser user) {
    put(KEY_USER, user);
  }
}
