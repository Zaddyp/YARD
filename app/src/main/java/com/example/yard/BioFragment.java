package com.example.yard;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yard.Adapter.SectionPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.parse.ParseUser;

public class BioFragment extends Fragment {
    View myFragment;
    ViewPager viewPager;
    TabLayout tabLayout;
    TextView profileName;
    TextView userSchool;
    TextView userTitle;

    public BioFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragment = inflater.inflate(R.layout.fragment_bio, container, false);
        viewPager = myFragment.findViewById(R.id.view_pager);
        tabLayout = myFragment.findViewById(R.id.tab_layout);
        return  myFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        profileName = getActivity().findViewById(R.id.tvProfileName);
        userSchool = getActivity().findViewById(R.id.tvProfileschool);
        userTitle = getActivity().findViewById(R.id.tvUserTitle);
        ParseUser currentUser = ParseUser.getCurrentUser();
        profileName.setText(currentUser.getUsername());
        userSchool.setText(currentUser.getString("school"));
        userTitle.setText(currentUser.getString("title"));
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new BioGridFragment(), "POSTS");
        adapter.addFragment(new BioTagFragment(), "TAGS");
        viewPager.setAdapter(adapter);
    }
}