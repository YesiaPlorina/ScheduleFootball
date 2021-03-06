package com.fiqri.haripertama.fragment.utama;


import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.fiqri.haripertama.R;
import com.fiqri.haripertama.adapter.MatchAdapter;
import com.fiqri.haripertama.adapter.TeamsAdapter;
import com.fiqri.haripertama.model.EventsItem;
import com.fiqri.haripertama.model.ResponseTeams;
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
public class Teams extends Fragment {

    Spinner spinnerTeam;
    RecyclerView rvTeam;
    SwipeRefreshLayout swipe;

    List<TeamsItem> data = new ArrayList<>();
    TeamsAdapter adapter;
    LinearLayoutManager manager;

    String penampung , id;

    public Teams() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_teams, container, false);
//        unbinder = ButterKnife.bind(this, view);
        rvTeam = view.findViewById(R.id.rv_team);
        swipe = view.findViewById(R.id.swipe);
        spinnerTeam = view.findViewById(R.id.spinner_team);
        initViews();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            data = savedInstanceState.getParcelableArrayList("state");
            adapter.setTeamsItemList(data);
            rvTeam.setAdapter(adapter);
        } else {
            sendRequestAllTeams("4328");
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
                        sendRequestAllTeams(id);
                    }
                }, 3000);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.leagueArray, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTeam.setAdapter(adapter);

        spinnerTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {
                penampung = parent.getItemAtPosition(index).toString();
                switch (penampung) {
                    case "English Premier League":
                        sendRequestAllTeams("4328");
                        break;

                    case "German Bundesliga":
                        sendRequestAllTeams("4331");
                        break;

                    case "Italian Serie A":
                        sendRequestAllTeams("4332");
                        break;

                    case "French Ligue 1":
                        sendRequestAllTeams("4334");
                        break;

                    case "Spanish La Liga":
                        sendRequestAllTeams("4335");
                        break;

                    case "Netherlands Eredivisie":
                        sendRequestAllTeams("4337");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing to do
            }
        });
    }

    private void sendRequestAllTeams(String id) {
        ConfigRetrofit.getInstanc().getAllTeams(id).enqueue(new Callback<ResponseTeams>() {
            @Override
            public void onResponse(Call<ResponseTeams> call, Response<ResponseTeams> response) {

                if (response.isSuccessful()) {
                    List<TeamsItem> data = response.body().getTeams();
                    setUpList(data);
                }
            }

            @Override
            public void onFailure(Call<ResponseTeams> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpList(List<TeamsItem> data) {
        adapter = new TeamsAdapter(getActivity());
        adapter.notifyDataSetChanged();
        adapter.setTeamsItemList(data);
        rvTeam.setAdapter(adapter);
    }

    private void initViews() {
        adapter = new TeamsAdapter(getActivity());
        adapter.setTeamsItemList(data);
        adapter.notifyDataSetChanged();
        rvTeam.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvTeam.setHasFixedSize(true);
        rvTeam.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("state", new ArrayList<Parcelable>(adapter.getTeamsItemList()));
    }

}
