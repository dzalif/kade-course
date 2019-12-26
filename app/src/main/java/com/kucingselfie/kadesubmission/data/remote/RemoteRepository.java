package com.kucingselfie.kadesubmission.data.remote;

import android.os.Handler;

import com.kucingselfie.kadesubmission.api.ApiClient;

import javax.inject.Singleton;

@Singleton
public class RemoteRepository {
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private ApiClient apiClient;
    private Handler handler = new Handler();

    public RemoteRepository(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

}
