package com.fiqri.haripertama.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import com.fiqri.haripertama.model.EventsItem;
import com.fiqri.haripertama.model.ResponseEvents;
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
public class NextMatches extends Fragment {

    List<EventsItem> data = new ArrayList<>();
    MatchAdapter adapter;
    String penampung, id;

    LinearLayoutManager manager;
    Spinner spinnerNext;
    RecyclerView recyclerviewNext;
    SwipeRefreshLayout swipeNext;

    public NextMatches() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_next_matches, container, false);
        spinnerNext = view.findViewById(R.id.spinner_next);
        recyclerviewNext = view.findViewById(R.id.recyclerview_next);
        swipeNext = view.findViewById(R.id.swipe_next);
        initViews();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            data = savedInstanceState.getParcelableArrayList("state");
            adapter.setEventsItemList(data);
            recyclerviewNext.setAdapter(adapter);
        } else {
        }

        swipeNext.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeNext.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeNext.setRefreshing(false);
                        sendRequestNextMatch(id);
                    }
                }, 3000);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.leagueArray, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNext.setAdapter(adapter);

        spinnerNext.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
                penampung = parent.getItemAtPosition(index).toString();
                switch (penampung) {
                    case "English Premier League":
                        sendRequestNextMatch("4328");
                        break;

                    case "German Bundesliga":
                        sendRequestNextMatch("4331");
                        break;

                    case "Italian Serie A":
                        sendRequestNextMatch("4332");
                        break;

                    case "French Ligue 1":
                        sendRequestNextMatch("4334");
                        break;

                    case "Spanish La Liga":
                        sendRequestNextMatch("4335");
                        break;

                    case "Netherlands Eredivisie":
                        sendRequestNextMatch("4337");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing to do
            }
        });
    }

    private void initViews() {
        adapter = new MatchAdapter(getActivity());
        recyclerviewNext.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter.setEventsItemList(data);
        adapter.notifyDataSetChanged();
        recyclerviewNext.setHasFixedSize(true);
        recyclerviewNext.setAdapter(adapter);
    }

    private void sendRequestNextMatch(String id) {
        ConfigRetrofit.getInstanc().getMatchNextLeague(id).enqueue(new Callback<ResponseEvents>() {
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
        recyclerviewNext.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("state", new ArrayList<>(adapter.getEventsItemList()));
    }
}
