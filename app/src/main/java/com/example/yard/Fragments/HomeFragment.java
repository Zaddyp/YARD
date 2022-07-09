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
import com.example.yard.HelperClass.QueryPosts;
import com.example.yard.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
  public static final String TAG = "HomeFragment";
  protected PostsAdapter adapter;
  protected List<PostCreation> allPosts;
  QueryPosts queryPosts;

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
    queryPosts = new QueryPosts();
    queryPosts.queryPosts(false, adapter, allPosts);
  }
}
