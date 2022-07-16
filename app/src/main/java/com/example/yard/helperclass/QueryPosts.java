package com.example.yard.helperclass;

import com.example.yard.adapter.PostCreation;
import com.example.yard.adapter.PostsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class QueryPosts {
  int postCount = 0;

  public void queryPosts(
      boolean isFromBio, PostsAdapter adapter, List<PostCreation> allPosts, Callback callback) {
    ParseQuery<PostCreation> query = ParseQuery.getQuery(PostCreation.class);
    query.include(PostCreation.KEY_USER);
    if (isFromBio) {
      query.whereEqualTo(PostCreation.KEY_USER, ParseUser.getCurrentUser());
    }
    query.setLimit(15);
    // create a method called count = 0, count = 15 + count,
    query.setSkip(postCount);
    query.addDescendingOrder(PostCreation.KEY_CREATED_AT);
    query.findInBackground(
        new FindCallback<PostCreation>() {
          @Override
          public void done(List<PostCreation> posts, ParseException e) {
            if (e != null) {
              return;
            }
            allPosts.addAll(posts);
            //            adapter.notifyDataSetChanged();
            postCount += posts.size();
            if (callback != null) {
              callback.call();
            }
          }
        });
  }

  public interface Callback {
    void call();
  }
}
