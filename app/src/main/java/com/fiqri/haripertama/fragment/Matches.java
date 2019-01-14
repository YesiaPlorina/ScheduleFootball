package com.fiqri.haripertama.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiqri.haripertama.R;
import com.fiqri.haripertama.adapter.TabsPagerAdapter;
import com.fiqri.haripertama.fragment.utama.LastMatches;
import com.fiqri.haripertama.fragment.utama.NextMatches;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Matches extends Fragment {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Unbinder unbinder;

    public Matches() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_matches, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPager(viewPager);
        tabs.setupWithViewPager(viewPager);
    }

    private void initViewPager(ViewPager pager) {
        TabsPagerAdapter adapter = new TabsPagerAdapter(getFragmentManager());
        adapter.fragment(new LastMatches(), "Last Mathces");
        adapter.fragment(new NextMatches(), "Next Mathces");
        pager.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
