package com.fiqri.haripertama;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.fiqri.haripertama.fragment.LastMatches;
import com.fiqri.haripertama.fragment.NextMatches;
import com.fiqri.haripertama.fragment.utama.Favorites;
import com.fiqri.haripertama.fragment.utama.Teams;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

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

        Fragment fragment = new LastMatches();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
        }

        navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment;
                    switch (menuItem.getItemId()) {
                        case R.id.nav_last:
                            fragment = new LastMatches();
                            fragmentTransition(fragment);
                            return true;

                        case R.id.nav_next:
                            fragment = new NextMatches();
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
        });
    }

    private void fragmentTransition(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment, fragment).commit();
    }

}
