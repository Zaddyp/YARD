package com.example.yard.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.yard.R;
import com.example.yard.adapter.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.parse.ParseUser;

import java.util.Objects;

@SuppressWarnings("ALL")
public class BioFragment extends Fragment {
  private View myFragment;
  private ViewPager viewPager;
  private TabLayout tabLayout;
  private TextView tvProfileName;
  private TextView tvUserSchool;
  private TextView tvUserTitle;

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
    tabLayout.setupWithViewPager(viewPager);
  }

  private void setUpViewPager(ViewPager viewPager) {
    SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());
    adapter.addFragment(new BioGridFragment(), "POSTS");
    adapter.addFragment(new BioTagFragment(), "TAGS");
    viewPager.setAdapter(adapter);
  }
}
