package com.example.yard.HelperClass;
import com.example.yard.Adapter.PostCreation;
import com.example.yard.Adapter.PostsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class QueryPosts {
  public void queryPosts(boolean isFromBio, PostsAdapter adapter, List<PostCreation> allPosts) {
    ParseQuery<PostCreation> query = ParseQuery.getQuery(PostCreation.class);
    query.include(PostCreation.KEY_USER);
    if (isFromBio) {
      query.whereEqualTo(PostCreation.KEY_USER, ParseUser.getCurrentUser());
    }
    query.setLimit(30);
    query.addDescendingOrder(PostCreation.KEY_CREATED_AT);
    query.findInBackground(
        new FindCallback<PostCreation>() {
          @Override
          public void done(List<PostCreation> posts, ParseException e) {
            if (e != null) {
              return;
            }
            allPosts.addAll(posts);
            adapter.notifyDataSetChanged();
          }
        });
  }
}
