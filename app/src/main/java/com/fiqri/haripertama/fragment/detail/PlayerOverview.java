package com.fiqri.haripertama.fragment.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiqri.haripertama.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayerOverview extends Fragment {


    public PlayerOverview() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_overview, container, false);
    }

}
