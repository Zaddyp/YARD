package com.example.yard.helperclass;

import com.example.yard.adapter.Post;
import com.example.yard.adapter.PostsAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class PostQueryHelper {
  int postCount = 0;

  public void queryPost(
      boolean isFromBio, PostsAdapter adapter, List<Post> allPosts, Callback callback) {
    ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
    query.include(Post.KEY_USER);
    if (isFromBio) {
      query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
    }
    query.setLimit(15);
    // create a method called count = 0, count = 15 + count,
    query.setSkip(postCount);
    query.addDescendingOrder(Post.KEY_CREATED_AT);
    query.findInBackground(
        new FindCallback<Post>() {
          @Override
          public void done(List<Post> posts, ParseException e) {
            if (e != null) {
              return;
            }
            allPosts.addAll(posts);
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
