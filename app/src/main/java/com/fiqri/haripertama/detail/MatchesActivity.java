package com.fiqri.haripertama.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fiqri.haripertama.R;
import com.fiqri.haripertama.adapter.MatchAdapter;
import com.fiqri.haripertama.model.EventsItem;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MatchesActivity extends AppCompatActivity {

    public static final String IMAGES = "IMAGES";
    public static final String SCORE_HOME = "SCORE_HOME";
    public static final String SCORE_AWAY = "SCORE_AWAY";
    public static final String GOAL_HOME = "GOAL_HOME";
    public static final String GOAL_AWAY = "GOAL_AWAY";

    public static final String GOAL_KIPER = "GOAL_AWAY";
    public static final String GOAL_DEF = "GOAL_AWAY";
    public static final String GOAL_MID = "GOAL_AWAY";
    public static final String GOAL_FOR = "GOAL_AWAY";
    public static final String GOAL_SUB = "GOAL_AWAY";


    @BindView(R.id.iv_match_detail)
    ImageView ivMatchDetail;
    @BindView(R.id.tv_home_score_detail)
    TextView tvHomeScoreDetail;
    @BindView(R.id.tv_away_score_detail)
    TextView tvAwayScoreDetail;
    @BindView(R.id.tv_home_cetak_goal)
    TextView tvHomeCetakGoal;
    @BindView(R.id.tv_away_cetak_goal)
    TextView tvAwayCetakGoal;
    @BindView(R.id.tv_name_kiper_home)
    TextView tvNameKiperHome;
    @BindView(R.id.tv_name_kiper_away)
    TextView tvNameKiperAway;
    @BindView(R.id.tv_def_home)
    TextView tvDefHome;
    @BindView(R.id.tv_def_away)
    TextView tvDefAway;
    @BindView(R.id.tv_mid_home)
    TextView tvMidHome;
    @BindView(R.id.tv_mid_away)
    TextView tvMidAway;
    @BindView(R.id.tv_for_home)
    TextView tvForHome;
    @BindView(R.id.tv_for_away)
    TextView tvForAway;
    @BindView(R.id.tv_home_sub)
    TextView tvHomeSub;
    @BindView(R.id.tv_away_sub)
    TextView tvAwaySub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);
        ButterKnife.bind(this);

        getIntentData();
    }

    private void getIntentData() {
        String images = getIntent().getStringExtra(IMAGES);
        String scoreHome = getIntent().getStringExtra(SCORE_HOME);
        String scoreAway = getIntent().getStringExtra(SCORE_AWAY);
        String goalHome = getIntent().getStringExtra(GOAL_HOME);
        String goalAway = getIntent().getStringExtra(GOAL_AWAY);
        String kiper = getIntent().getStringExtra(GOAL_KIPER);

        tvHomeScoreDetail.setText(scoreHome);
        tvAwayScoreDetail.setText(scoreAway);
        tvHomeCetakGoal.setText(goalHome);
        tvAwayCetakGoal.setText(goalAway);
        tvNameKiperHome.setText(kiper);
        Picasso.get().load(images).into(ivMatchDetail);
    }
}
