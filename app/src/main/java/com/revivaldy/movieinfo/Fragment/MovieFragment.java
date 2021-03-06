package com.revivaldy.movieinfo.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.revivaldy.movieinfo.R;
import com.revivaldy.movieinfo.adapter.MovieAdapter;
import com.revivaldy.movieinfo.modal.MovieList;
import com.revivaldy.movieinfo.viewmodel.MovieViewModel;


public class MovieFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private MovieAdapter adapter;
    private MovieViewModel movieViewModel;
    private RecyclerView rv;
    private SwipeRefreshLayout swipe;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        rv = view.findViewById(R.id.rv_movie);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));

        swipe = view.findViewById(R.id.swipe_container);

        swipe.setRefreshing(true);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, getMovie);

        swipe.setOnRefreshListener(this);

        return view;
    }

    private Observer<MovieList> getMovie = new Observer<MovieList>() {
        @Override
        public void onChanged(@Nullable MovieList movieList) {
            adapter = new MovieAdapter(getContext(), movieList.getResults());
            rv.setAdapter(adapter);
            swipe.setRefreshing(false);
        }
    };

    @Override
    public void onRefresh() {
        Log.d("OnRefresh", "On Refresh State");
        swipe.setRefreshing(true);
        movieViewModel.loadMovies();
        movieViewModel.getMovies().observe(this, new Observer<MovieList>() {
            @Override
            public void onChanged(@Nullable MovieList movieList) {
                Log.d("OnChangedRefresh", "Changed Complete");
                swipe.setRefreshing(false);
                adapter = new MovieAdapter(getContext(), movieList.getResults());
                rv.setAdapter(adapter);
            }
        });
    }
}
