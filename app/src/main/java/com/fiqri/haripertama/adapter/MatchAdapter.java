package com.fiqri.haripertama.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fiqri.haripertama.R;
import com.fiqri.haripertama.detail.MatchesActivity;
import com.fiqri.haripertama.model.EventsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fiqri.haripertama.detail.MatchesActivity.SCORE_HOME;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {

    private Context ctx;
    private List<EventsItem> eventsItemList;



    // function constructor => pengakses didalam activity / fragment
    public MatchAdapter(Context ctx) {
        this.ctx = ctx;
    }

    public List<EventsItem> getEventsItemList() {
        return eventsItemList;
    }

    public void setEventsItemList(List<EventsItem> eventsItemList) {
        this.eventsItemList = eventsItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_match, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        // tampung dalam varibale dan get data api nya
        String tanggal = eventsItemList.get(position).getDateEvent();
        String tuanRumah = eventsItemList.get(position).getStrHomeTeam();
        String teamLawan = eventsItemList.get(position).getStrAwayTeam();
        final String scoreTuanRumah = eventsItemList.get(position).getIntHomeScore();
        final String scoreLawan = eventsItemList.get(position).getIntAwayScore();
        final String images = eventsItemList.get(position).getStrThumb();
        final String goalHome = eventsItemList.get(position).getStrHomeGoalDetails();
        final String goalAway = eventsItemList.get(position).getStrAwayGoalDetails();

        final String kiper = eventsItemList.get(position).getStrHomeLineupGoalkeeper();

        // tampilkan ke dalam text view nya
        viewHolder.dateScheduleTv.setText(tanggal);
        viewHolder.tvHomeName.setText(tuanRumah);
        viewHolder.tvAwayName.setText(teamLawan);
        viewHolder.tvHomeScore.setText(scoreTuanRumah);
        viewHolder.tvAwayScore.setText(scoreLawan);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent kirim = new Intent(ctx, MatchesActivity.class);
                kirim.putExtra(MatchesActivity.IMAGES, images);
                kirim.putExtra(SCORE_HOME, scoreTuanRumah);
                kirim.putExtra(MatchesActivity.SCORE_AWAY, scoreLawan);
                kirim.putExtra(MatchesActivity.GOAL_HOME, goalHome);
                kirim.putExtra(MatchesActivity.GOAL_AWAY, goalAway);
                kirim.putExtra(MatchesActivity.GOAL_KIPER, kiper);
                ctx.startActivity(kirim);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (eventsItemList == null) return 0;
        return eventsItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // deklarasi dan inisialisasi id nya masing"
        @BindView(R.id.tv_home_name)
        TextView tvHomeName;
        @BindView(R.id.tv_home_score)
        TextView tvHomeScore;
        @BindView(R.id.tv_away_score)
        TextView tvAwayScore;
        @BindView(R.id.tv_away_name)
        TextView tvAwayName;
        @BindView(R.id.dateScheduleTv)
        TextView dateScheduleTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
