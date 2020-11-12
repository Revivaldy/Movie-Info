package com.revivaldy.movieinfo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.revivaldy.movieinfo.modal.Movie;
import com.revivaldy.movieinfo.modal.TvShow;
//import com.revivaldy.movieinfo.modal.TvShow;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_SHOW = "extra_show";

    private String url_image = "https://image.tmdb.org/t/p/w185";
    private String imgMovie, imgShow;
    private ConstraintLayout layoutDetail;
    private boolean isEdit = false;
    public static final int RESULT_ADD = 101;
    private int position;
    private int idPrimary;

    TextView tvTitle, tvDate, tvVoteCount, tvRating, tvOverview;
    ImageView imgPoster;
    Button btnAddFav, btnRemoveFav;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        prepare();

        if (getIntent().getStringExtra("cek_data").equals("movie") ||
                getIntent().getStringExtra("cek_data").equals("fav_movie")) {
            getMovieDetail();
        }
        if (getIntent().getStringExtra("cek_data").equals("tv_show") ||
                getIntent().getStringExtra("cek_data").equals("fav_shows")) {
            getShowDetail();
        }
    }

    private void prepareToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        switch (getIntent().getStringExtra("cek_data")) {
            case "movie":
                collapsingToolbarLayout.setTitle(getString(R.string.mov_detail));
                break;
            case "fav_movie":
                collapsingToolbarLayout.setTitle(getString(R.string.fav_mov_detail));
                break;
            case "tv_show":
                collapsingToolbarLayout.setTitle(getString(R.string.shows_detail));
                break;
            case "fav_shows":
                collapsingToolbarLayout.setTitle(getString(R.string.fav_shows_detail));
                break;
        }

        collapsingToolbarLayout.setCollapsedTitleTextColor(
                ContextCompat.getColor(this, R.color.colorLight));
        collapsingToolbarLayout.setExpandedTitleColor(
                ContextCompat.getColor(this, R.color.colorLight));
    }

    private void prepare() {
        prepareToolBar();

        tvTitle = findViewById(R.id.tv_detail);
        tvDate = findViewById(R.id.tv_tanggal);
        tvVoteCount = findViewById(R.id.tv_vote_count);
        tvRating = findViewById(R.id.tv_rating);
        tvOverview = findViewById(R.id.tv_description);
        imgPoster = findViewById(R.id.img_poster_detail);
        progressBar = findViewById(R.id.progressBarShowDetail);
        layoutDetail = findViewById(R.id.layout_detail);
        btnAddFav = findViewById(R.id.btn_fav_add);
        btnRemoveFav = findViewById(R.id.btn_fav_remove);

        btnAddFav.setOnClickListener(this);
        btnRemoveFav.setOnClickListener(this);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private void getMovieDetail() {

        progressBar.setVisibility(View.VISIBLE);
        layoutDetail.setVisibility(View.GONE);

        if (getIntent().getStringExtra("cek_data").equals("movie")) {
            getSupportActionBar().setTitle(getString(R.string.mov_detail));

            Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

            imgMovie = url_image + movie.getPosterPath();
            idPrimary = movie.getId();
            Log.d("testId", "id : " + idPrimary);

            tvTitle.setText(movie.getTitle());
            tvRating.setText(String.valueOf(movie.getVoteAverage()));
            tvVoteCount.setText(String.valueOf(movie.getVoteCount()));
            tvDate.setText(movie.getReleaseDate());
            tvOverview.setText(movie.getOverview());
            Glide.with(DetailActivity.this)
                    .load(imgMovie)
                    .into(imgPoster);
        }



        layoutDetail.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }





    private void getShowDetail() {
        progressBar.setVisibility(View.VISIBLE);
        layoutDetail.setVisibility(View.GONE);

        if (getIntent().getStringExtra("cek_data").equals("tv_show")) {
            getSupportActionBar().setTitle(getString(R.string.shows_detail));

            TvShow tvShow = getIntent().getParcelableExtra(EXTRA_SHOW);

            imgShow = url_image + tvShow.getPosterPath();
            idPrimary = tvShow.getId();
            Log.d("testId", "id : " + idPrimary);

            tvTitle.setText(tvShow.getName());
            tvRating.setText(String.valueOf(tvShow.getVoteAverage()));
            tvVoteCount.setText(String.valueOf(tvShow.getVoteCount()));
            tvDate.setText(tvShow.getAirDate());
            tvOverview.setText(tvShow.getOverview());
            Glide.with(DetailActivity.this)
                    .load(imgShow)
                    .into(imgPoster);
        }



        layoutDetail.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View view) {
        int idFav = idPrimary;
        String title = tvTitle.getText().toString().trim();
        String voteCount = tvVoteCount.getText().toString().trim();
        String overview = tvOverview.getText().toString().trim();
        String releaseDate = tvDate.getText().toString().trim();

        String voteAverage = tvRating.getText().toString().trim();


    }

}
