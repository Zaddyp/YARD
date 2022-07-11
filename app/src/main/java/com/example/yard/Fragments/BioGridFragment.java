package com.example.yard.Fragments;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yard.Adapter.PostsAdapter;
import com.example.yard.HelperClass.QueryPosts;
import com.example.yard.R;

import java.util.ArrayList;

public class BioGridFragment extends HomeFragment {
  protected RecyclerView rvPosts;
  QueryPosts queryPosts;

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    rvPosts = view.findViewById(R.id.rvPosts);
    allPosts = new ArrayList<>();
    adapter = new PostsAdapter(getContext(), allPosts);
    rvPosts.setAdapter(adapter);
    queryPosts = new QueryPosts();
    queryPosts.queryPosts(true, adapter, allPosts);
  }
}
