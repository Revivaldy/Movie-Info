package com.revivaldy.movieinfo.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.revivaldy.movieinfo.api.ApiClient;
import com.revivaldy.movieinfo.api.ApiService;
import com.revivaldy.movieinfo.modal.TvShowList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvViewModel extends ViewModel {
    private static String api = ApiClient.getApiKey();
    private MutableLiveData<TvShowList> listTvShow;

    public void loadShows() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Log.d("TvViewModel", "api key: " + api);

        Call<TvShowList> showsCall = service.getShowList(api);
        showsCall.enqueue(new Callback<TvShowList>() {
            @Override
            public void onResponse(Call<TvShowList> call, Response<TvShowList> response) {
                if (response.isSuccessful()) {
                    listTvShow.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TvShowList> call, Throwable t) {
                Log.e("onFailureShows", t.getMessage());
            }
        });
    }

    public LiveData<TvShowList> getShows() {
        if (listTvShow == null) {
            listTvShow = new MutableLiveData<>();
            loadShows();
        }
        return listTvShow;
    }
}
