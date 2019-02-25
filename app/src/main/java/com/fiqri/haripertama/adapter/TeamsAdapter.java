package com.fiqri.haripertama.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fiqri.haripertama.R;
import com.fiqri.haripertama.detail.TeamsActivity;
import com.fiqri.haripertama.model.TeamsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {

    private Context ctx;
    private List<TeamsItem> teamsItemList;

    public TeamsAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public List<TeamsItem> getTeamsItemList() {
        return teamsItemList;
    }

    public void setTeamsItemList(List<TeamsItem> teamsItemList) {
        this.teamsItemList = teamsItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_teams, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final String images = teamsItemList.get(position).getStrTeamBadge();
        final String nameTeams = teamsItemList.get(position).getStrTeam();
        Picasso.get().load(images).into(viewHolder.ivTeam);
        viewHolder.tvTeam.setText(nameTeams);

        // id teams nya ambil dulu
        final String idTeams = teamsItemList.get(position).getIdTeam();

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // baru kirim
//                TeamsItem data = new TeamsItem();
//                data.setIdTeam(idTeams);

                Intent kirim = new Intent(ctx, TeamsActivity.class);
                kirim.putExtra("key", idTeams);
                ctx.startActivity(kirim);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (teamsItemList == null) return 0;
        return teamsItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_team)
        ImageView ivTeam;
        @BindView(R.id.tv_team)
        TextView tvTeam;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}