package com.example.yard.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yard.Adapter.PostCreation;
import com.example.yard.Adapter.PostsAdapter;
import com.example.yard.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
  public static final String TAG = "HomeFragment";
  protected PostsAdapter adapter;
  protected List<PostCreation> allPosts;

  public HomeFragment() {}

  @Nullable
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_home, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    RecyclerView rvPosts = view.findViewById(R.id.rvPosts);
    allPosts = new ArrayList<>();
    adapter = new PostsAdapter(getContext(), allPosts);
    rvPosts.setAdapter(adapter);
    rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
    queryPosts();
  }

  protected void queryPosts() {
    ParseQuery<PostCreation> query = ParseQuery.getQuery(PostCreation.class);
    query.include(PostCreation.KEY_USER);
    query.setLimit(30);
    query.addDescendingOrder(PostCreation.KEY_CREATED_AT);
    query.findInBackground(
            (posts, e) -> {
              if (e != null) {
                return;
              }
              allPosts.addAll(posts);
              adapter.notifyDataSetChanged();
            });
  }
}
