package com.fiqri.haripertama;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.fiqri.haripertama.fragment.Matches;
import com.fiqri.haripertama.fragment.utama.Favorites;
import com.fiqri.haripertama.fragment.utama.NextMatches;
import com.fiqri.haripertama.fragment.utama.Teams;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_main)
    Toolbar toolbar;
    @BindView(R.id.container_fragment)
    FrameLayout containerFragment;
    @BindView(R.id.nav_bottom)
    BottomNavigationView navBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Fragment fragment = new Matches();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
        }
        navBottom.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.nav_match:
                    fragment = new Matches();
                    fragmentTransition(fragment);
                    return true;
                case R.id.nav_teams:
                    fragment = new Teams();
                    fragmentTransition(fragment);
                    return true;
                case R.id.nav_nav_fav:
                    fragment = new Favorites();
                    fragmentTransition(fragment);
                    return true;
            }
            return false;
        }
    };

    private void fragmentTransition(Fragment fragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
    }
}
