package com.example.yard.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yard.Adapter.PostCreation;
import com.example.yard.Adapter.PostsAdapter;
import com.example.yard.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class BioGridFragment extends HomeFragment {
  protected RecyclerView rvPosts;

  @Override
  protected void queryPosts() {
    ParseQuery<PostCreation> query = ParseQuery.getQuery(PostCreation.class);
    query.include(PostCreation.KEY_USER);
    query.whereEqualTo(PostCreation.KEY_USER, ParseUser.getCurrentUser());
    query.setLimit(20);
    query.addDescendingOrder(PostCreation.KEY_CREATED_AT);
    query.findInBackground(
            (posts, e) -> {
              if (e != null) {
                //                    Toast.makeText(HomeFragment.this, "The list of posts is empty
                // and the query isnt getting anything", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Issue with getting posts", e);
                return;
              }
              allPosts.addAll(posts);
              adapter.notifyDataSetChanged();
            });
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    rvPosts = view.findViewById(R.id.rvPosts);
    allPosts = new ArrayList<>();
    adapter = new PostsAdapter(getContext(), allPosts);
    rvPosts.setAdapter(adapter);
    queryPosts();
  }
}
