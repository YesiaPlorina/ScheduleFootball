package com.fiqri.haripertama.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.fiqri.haripertama.R;
import com.fiqri.haripertama.adapter.MatchAdapter;
import com.fiqri.haripertama.adapter.TeamsAdapter;
import com.fiqri.haripertama.model.EventsItem;
import com.fiqri.haripertama.model.ResponseEvents;
import com.fiqri.haripertama.model.TeamsItem;
import com.fiqri.haripertama.restApi.ConfigRetrofit;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastMatches extends Fragment {

    RecyclerView recyclerviewMatches;
    SwipeRefreshLayout swipe;
    Spinner spinnerMatch;


    List<EventsItem> data = new ArrayList<>();
    MatchAdapter adapter;
    String penampung, id;
    LinearLayoutManager manager;

    public LastMatches() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_last_matches, container, false);
        recyclerviewMatches = view.findViewById(R.id.recyclerview_matches);
        swipe = view.findViewById(R.id.swipe);
        spinnerMatch = view.findViewById(R.id.spinner_match);
        initViews();
        return view;
    }

    private void initViews() {
        adapter = new MatchAdapter(getActivity());
        recyclerviewMatches.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.notifyDataSetChanged();
        adapter.setEventsItemList(data);
        recyclerviewMatches.setHasFixedSize(true);
        recyclerviewMatches.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            data = savedInstanceState.getParcelableArrayList("STATE_DATA");
            adapter.setEventsItemList(data);
            recyclerviewMatches.setAdapter(adapter);
        } else {
            sendRequestLastMatch("4328");
        }

        swipe.setColorScheme(
                android.R.color.holo_blue_bright);

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                        sendRequestLastMatch(id);
                    }
                }, 3000);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.leagueArray, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMatch.setAdapter(adapter);

        spinnerMatch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
                penampung = parent.getItemAtPosition(index).toString();
                switch (penampung) {
                    case "English Premier League":
                        sendRequestLastMatch("4328");
                        break;

                    case "German Bundesliga":
                        sendRequestLastMatch("4331");
                        break;

                    case "Italian Serie A":
                        sendRequestLastMatch("4332");
                        break;

                    case "French Ligue 1":
                        sendRequestLastMatch("4334");
                        break;

                    case "Spanish La Liga":
                        sendRequestLastMatch("4335");
                        break;

                    case "Netherlands Eredivisie":
                        sendRequestLastMatch("4337");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing to do
            }
        });
    }

    private void sendRequestLastMatch(String id) {
        ConfigRetrofit.getInstanc().getMatchPastLeague(id).enqueue(new Callback<ResponseEvents>() {
            @Override
            public void onResponse(Call<ResponseEvents> call, Response<ResponseEvents> response) {

                if (response.isSuccessful()) {
                    data = response.body().getEvents();
                    setUpList(data);
                }
            }

            @Override
            public void onFailure(Call<ResponseEvents> call, Throwable t) {

            }
        });
    }

    private void setUpList(List<EventsItem> data) {
        adapter = new MatchAdapter(getActivity());
        adapter.notifyDataSetChanged();
        adapter.setEventsItemList(data);
        recyclerviewMatches.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("STATE_DATA", new ArrayList<>(adapter.getEventsItemList()));
    }
}
