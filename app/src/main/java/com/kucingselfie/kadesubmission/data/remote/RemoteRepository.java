package com.kucingselfie.kadesubmission.data.remote;

import android.os.Handler;

import com.kucingselfie.kadesubmission.api.ApiClient;
import com.kucingselfie.kadesubmission.api.response.ListLeagueResponse;
import com.kucingselfie.kadesubmission.api.response.SearchResponse;
import com.kucingselfie.kadesubmission.model.League;
import com.kucingselfie.kadesubmission.model.Search;
import com.kucingselfie.kadesubmission.util.EspressoIdlingResource;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.kucingselfie.kadesubmission.common.ConstantKt.ENGLAND;
import static com.kucingselfie.kadesubmission.common.ConstantKt.SOCCER;

@Singleton
public class RemoteRepository {
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private ApiClient apiClient;
    private Handler handler = new Handler();

    public RemoteRepository(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public void getListLeagues(final LoadListLeagueCallback callback) {
        EspressoIdlingResource.increment();
        handler.postDelayed(() -> apiClient.create().getListLeague(ENGLAND, SOCCER).enqueue(new Callback<ListLeagueResponse>() {
            @Override
            public void onResponse(@NotNull Call<ListLeagueResponse> call, @NotNull Response<ListLeagueResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body() != null ? response.body().getCountries() : null);
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ListLeagueResponse> call, @NotNull Throwable t) {
                callback.onError(t.getMessage());
            }
        }), SERVICE_LATENCY_IN_MILLIS);
    }

    public void search(String query, final SearchMatchCallback callback) {
        EspressoIdlingResource.increment();
        handler.postDelayed(() -> apiClient.create().searchEvents(query, SOCCER).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(@NotNull Call<SearchResponse> call, @NotNull Response<SearchResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body() != null ? response.body().getEvent() : null);
                    EspressoIdlingResource.decrement();
                }
            }

            @Override
            public void onFailure(@NotNull Call<SearchResponse> call, @NotNull Throwable t) {
                callback.onError(t.getMessage());
            }
        }), SERVICE_LATENCY_IN_MILLIS);
    }

    public interface LoadListLeagueCallback {
        void onSuccess(List<League> response);
        void onError(String message);
    }

    public interface SearchMatchCallback {
        void onSuccess(List<Search> response);
        void onError(String message);
    }

}
