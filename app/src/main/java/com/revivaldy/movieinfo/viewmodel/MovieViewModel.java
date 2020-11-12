package com.revivaldy.movieinfo.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.revivaldy.movieinfo.api.ApiClient;
import com.revivaldy.movieinfo.api.ApiService;
import com.revivaldy.movieinfo.modal.MovieList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private String api = ApiClient.getApiKey();
    private MutableLiveData<MovieList> listMovies;

    public void loadMovies() {
        ApiService service = ApiClient.getClient().create(ApiService.class);

        Call<MovieList> movieCall = service.getMovieList(api);

        movieCall.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                if (response.isSuccessful()) {
                    listMovies.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.e("onFailureMovie", t.getMessage());
            }
        });
    }

    public LiveData<MovieList> getMovies() {
        if (listMovies == null) {
            listMovies = new MutableLiveData<>();
            loadMovies();
        }
        return listMovies;
    }
}
