package com.fiqri.haripertama.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.fiqri.haripertama.MainActivity;
import com.fiqri.haripertama.R;
import com.fiqri.haripertama.adapter.MatchAdapter;
import com.fiqri.haripertama.adapter.PlayersAdapter;
import com.fiqri.haripertama.fragment.utama.Teams;
import com.fiqri.haripertama.model.EventsItem;
import com.fiqri.haripertama.model.PlayerItem;
import com.fiqri.haripertama.model.ResponseEvents;
import com.fiqri.haripertama.model.ResponsePlayers;
import com.fiqri.haripertama.model.TeamsItem;
import com.fiqri.haripertama.restApi.ConfigRetrofit;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TeamsActivity extends AppCompatActivity {

    @BindView(R.id.iv_teams_detail)
    ImageView ivTeamsDetail;
    @BindView(R.id.recyclerview_player)
    RecyclerView recyclerviewPlayer;

    List<PlayerItem> data;
    PlayersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        ButterKnife.bind(this);

        initViews();

        // terima id teams yang dikirim
        String idTeams = getIntent().getStringExtra("key");
        sendRequestAllPlayers(idTeams);
    }

    private void initViews() {
        adapter = new PlayersAdapter(this);
        recyclerviewPlayer.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.notifyDataSetChanged();
        adapter.setPlayerItems(data);
        recyclerviewPlayer.setHasFixedSize(true);
        recyclerviewPlayer.setAdapter(adapter);
    }


    private void sendRequestAllPlayers(String teamId) {
        ConfigRetrofit.getInstanc().getAllPlayers(teamId).enqueue(new Callback<ResponsePlayers>() {
            @Override
            public void onResponse(Call<ResponsePlayers> call, Response<ResponsePlayers> response) {

                if (response.isSuccessful()) {
                    data = response.body().getPlayer();
                    setUpList(data);
                }
            }

            @Override
            public void onFailure(Call<ResponsePlayers> call, Throwable t) {

            }
        });
    }

    private void setUpList(List<PlayerItem> data) {
        adapter = new PlayersAdapter(TeamsActivity.this);
        adapter.notifyDataSetChanged();
        adapter.setPlayerItems(data);
        recyclerviewPlayer.setAdapter(adapter);
    }
}
