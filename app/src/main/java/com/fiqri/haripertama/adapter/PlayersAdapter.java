package com.fiqri.haripertama.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fiqri.haripertama.R;
import com.fiqri.haripertama.model.PlayerItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ViewHolder> {

    Context ctx;
    List<PlayerItem> playerItems;

    public PlayersAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public List<PlayerItem> getPlayerItems() {
        return playerItems;
    }

    public void setPlayerItems(List<PlayerItem> playerItems) {
        this.playerItems = playerItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_players, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.get().load(playerItems.get(i).getStrCutout()).into(viewHolder.ivPlayer);
    }

    @Override
    public int getItemCount() {
        if (playerItems == null) return 0;
        return playerItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_player)
        ImageView ivPlayer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
