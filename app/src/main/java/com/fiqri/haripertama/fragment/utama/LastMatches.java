package com.fiqri.haripertama.fragment.utama;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiqri.haripertama.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastMatches extends Fragment {


    public LastMatches() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_matches, container, false);
    }

}
