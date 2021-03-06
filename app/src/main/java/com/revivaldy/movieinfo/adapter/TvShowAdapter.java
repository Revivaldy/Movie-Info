package com.revivaldy.movieinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.revivaldy.movieinfo.R;
import com.revivaldy.movieinfo.DetailActivity;
import com.revivaldy.movieinfo.modal.TvShow;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvViewHolder> {
    private Context context;
    private List<TvShow> showList;

    public TvShowAdapter(Context context, List<TvShow> showList) {
        this.context = context;
        this.showList = showList;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row = LayoutInflater.from(context).inflate(R.layout.item_show_cardview, viewGroup, false);
        return new TvViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder tvViewHolder, int i) {
        tvViewHolder.bind(showList.get(i));
    }

    @Override
    public int getItemCount() {
        return showList.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPoster;
        TextView tvTitle, tvReleaseDate, tvRating, tvVote;

        public TvViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_judul);
            tvReleaseDate = itemView.findViewById(R.id.tv_item_tanggal);
            tvRating = itemView.findViewById(R.id.tv_item_rating);
            tvVote = itemView.findViewById(R.id.tv_item_vote);

            imgPoster = itemView.findViewById(R.id.img_poster_show);

            itemView.setOnClickListener(this);
        }

        void bind(TvShow tvShow) {
            String url_image = "https://image.tmdb.org/t/p/w185" + tvShow.getPosterPath();

            tvTitle.setText(tvShow.getName());
            tvReleaseDate.setText(tvShow.getAirDate());
            tvRating.setText(Double.toString(tvShow.getVoteAverage()));
            tvVote.setText(Double.toString(tvShow.getVoteCount()));

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .into(imgPoster);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            TvShow tvShow = showList.get(position);

            Intent detailShowIntent = new Intent(view.getContext(), DetailActivity.class);
            detailShowIntent.putExtra("cek_data", "tv_show");
            detailShowIntent.putExtra(DetailActivity.EXTRA_SHOW, tvShow);
            view.getContext().startActivity(detailShowIntent);
        }
    }
}
